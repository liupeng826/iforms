package com.microastudio.iforms.modules.system.service;


import com.microastudio.iforms.modules.system.domain.Dept;
import com.microastudio.iforms.modules.system.dto.DeptDto;
import com.microastudio.iforms.modules.system.dto.DeptQueryCriteria;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author peng
 */
public interface DeptService {

    /**
     * 查询所有数据
     *
     * @param criteria 条件
     * @return /
     */
    List<DeptDto> queryAll(DeptQueryCriteria criteria);

    List<DeptDto> findAll();

    /**
     * 根据ID查询
     *
     * @param id /
     * @return /
     */
    DeptDto findById(String id);

    Dept findByIdAndIsActive(String id);

    /**
     * 创建
     *
     * @param resources /
     * @return /
     */
    DeptDto create(Dept resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Dept resources);

    /**
     * 删除
     *
     * @param deptDtos /
     */
    void delete(Set<DeptDto> deptDtos);

//    /**
//     * 构建树形数据
//     *
//     * @param deptDtos 原始数据
//     * @return /
//     */
//    Object buildTree(List<DeptDto> deptDtos);
//
//    /**
//     * 根据PID查询
//     *
//     * @param pid /
//     * @return /
//     */
//    List<Dept> findByPid(long pid);
//
//    /**
//     * 根据角色ID查询
//     *
//     * @param id /
//     * @return /
//     */
//    Set<Dept> findByRoleIds(Long id);

    long countByIdAndMarketIdAndIsActive(String id, String marketId, byte isActive);

    /**
     * 导出数据
     *
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<DeptDto> queryAll, HttpServletResponse response) throws IOException;

//    /**
//     * 获取待删除的部门
//     *
//     * @param deptList /
//     * @param deptDtos /
//     * @return /
//     */
//    Set<DeptDto> getDeleteDepts(List<Dept> deptList, Set<DeptDto> deptDtos);
}
