package com.microastudio.iforms.modules.system.service.impl;


import com.microastudio.iforms.common.exception.BadRequestException;
import com.microastudio.iforms.common.utils.FileUtil;
import com.microastudio.iforms.common.utils.QueryHelp;
import com.microastudio.iforms.common.utils.ValidationUtil;
import com.microastudio.iforms.modules.system.domain.Dept;
import com.microastudio.iforms.modules.system.dto.DeptDto;
import com.microastudio.iforms.modules.system.dto.DeptQueryCriteria;
import com.microastudio.iforms.modules.system.mapper.DeptMapper;
import com.microastudio.iforms.modules.system.repository.DeptRepository;
import com.microastudio.iforms.modules.system.service.DeptService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author peng
 */
@Service
@CacheConfig(cacheNames = "dept")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptRepository deptRepository, DeptMapper deptMapper) {
        this.deptRepository = deptRepository;
        this.deptMapper = deptMapper;
    }

    @Override
    @Cacheable
    public List<DeptDto> queryAll(DeptQueryCriteria criteria) {
        return deptMapper.toDto(deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Cacheable
    public List<DeptDto> findAll() {
        return deptMapper.toDto(deptRepository.findAll());
    }

    @Override
    @Cacheable(key = "#p0")
    public DeptDto findById(String id) {
        Dept dept = deptRepository.findById(id).orElseGet(Dept::new);
        ValidationUtil.isNull(dept.getId(), "Dept", "id", id);
        return deptMapper.toDto(dept);
    }

//    @Override
//    @Cacheable
//    public List<Dept> findByPid(long pid) {
//        return deptRepository.findByPid(pid);
//    }

    @Override
    public Dept findByIdAndIsActive(String id) {
        return deptRepository.findByIdAndIsActive(id, Byte.valueOf("1"));
    }

//    @Override
//    public Set<Dept> findByRoleIds(Long id) {
//        return deptRepository.findByRoles_Id(id);
//    }

    @Override
    public long countByIdAndMarketIdAndIsActive(String id, String marketId, byte isActive) {
        return deptRepository.countByIdAndMarketIdAndIsActive(id, marketId, isActive);
    }

//    @Override
//    @Cacheable
//    public Object buildTree(List<DeptDto> deptDtos) {
//        Set<DeptDto> trees = new LinkedHashSet<>();
//        Set<DeptDto> depts = new LinkedHashSet<>();
//        List<String> deptNames = deptDtos.stream().map(DeptDto::getName).collect(Collectors.toList());
//        boolean isChild;
//        for (DeptDto deptDTO : deptDtos) {
//            isChild = false;
//            if ("0".equals(deptDTO.getMarketId().toString())) {
//                trees.add(deptDTO);
//            }
//            for (DeptDto it : deptDtos) {
//                if (it.getMarketId().equals(deptDTO.getId())) {
//                    isChild = true;
//                    if (deptDTO.getChildren() == null) {
//                        deptDTO.setChildren(new ArrayList<>());
//                    }
//                    deptDTO.getChildren().add(it);
//                }
//            }
//            if (isChild) {
//                depts.add(deptDTO);
//            } else if (!deptNames.contains(deptRepository.findNameById(deptDTO.getId()))) {
//                depts.add(deptDTO);
//            }
//        }
//
//        if (CollectionUtils.isEmpty(trees)) {
//            trees = depts;
//        }
//
//        Integer totalElements = deptDtos.size();
//
//        Map<String, Object> map = new HashMap<>(2);
//        map.put("totalElements", totalElements);
//        map.put("content", CollectionUtils.isEmpty(trees) ? deptDtos : trees);
//        return map;
//    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public DeptDto create(Dept resources) {
        return deptMapper.toDto(deptRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Dept resources) {
        if (resources.getId().equals(resources.getMarketId())) {
            throw new BadRequestException("上级不能为自己");
        }
        Dept dept = deptRepository.findById(resources.getId()).orElseGet(Dept::new);
        ValidationUtil.isNull(dept.getId(), "Dept", "id", resources.getId());
        resources.setId(dept.getId());
        deptRepository.save(resources);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<DeptDto> deptDtos) {
        for (DeptDto deptDto : deptDtos) {
            deptRepository.deleteById(deptDto.getId());
        }
    }

    @Override
    public void download(List<DeptDto> deptDtos, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DeptDto deptDTO : deptDtos) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("部门名称", deptDTO.getName());
            map.put("部门状态", deptDTO.getIsActive() == 1 ? "启用" : "停用");
            map.put("创建日期", deptDTO.getCreatedDate());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

//    @Override
//    public Set<DeptDto> getDeleteDepts(List<Dept> menuList, Set<DeptDto> deptDtos) {
//        for (Dept dept : menuList) {
//            deptDtos.add(toDto(dept));
//            List<Dept> depts = deptRepository.findByPid(dept.getId());
//            if (depts != null && depts.size() != 0) {
//                getDeleteDepts(depts, deptDtos);
//            }
//        }
//        return deptDtos;
//    }
}
