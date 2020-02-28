package com.microastudio.iforms.modules.system.service.impl;

import com.microastudio.iforms.common.exception.EntityExistException;
import com.microastudio.iforms.common.exception.EntityNotFoundException;
import com.microastudio.iforms.common.utils.*;
import com.microastudio.iforms.modules.system.domain.Dept;
import com.microastudio.iforms.modules.system.domain.User;
import com.microastudio.iforms.modules.system.dto.UserDto;
import com.microastudio.iforms.modules.system.dto.UserQueryCriteria;
import com.microastudio.iforms.modules.system.mapper.UserMapper;
import com.microastudio.iforms.modules.system.repository.DeptRepository;
import com.microastudio.iforms.modules.system.repository.UserRepository;
import com.microastudio.iforms.modules.system.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author peng
 */
@Service
@CacheConfig(cacheNames = "user")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DeptRepository deptRepository;
    private final UserMapper userMapper;
    private final RedisUtils redisUtils;

    public UserServiceImpl(UserRepository userRepository, DeptRepository deptRepository, UserMapper userMapper, RedisUtils redisUtils) {
        this.userRepository = userRepository;
        this.deptRepository = deptRepository;
        this.userMapper = userMapper;
        this.redisUtils = redisUtils;
    }

    @Override
    @Cacheable
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }

    @Override
    @Cacheable
    public List<UserDto> queryAll(UserQueryCriteria criteria) {
        List<User> users = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder));
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(toDto(user));
        }
        return userDtos;
    }

    @Override
    @Cacheable(key = "#p0")
    public UserDto findById(long id) {
        User user = userRepository.findById(id).orElseGet(User::new);
        ValidationUtil.isNull(user.getId(), "User", "id", id);
        return toDto(user);
    }

    @Override
    public UserDto findByUserId(String id) {
        User user = userRepository.findByUserId(id);
        ValidationUtil.isNull(user.getId(), "User", "id", id);
        return toDto(user);
    }

    @Override
    public long countByUserIdAndIsActive(String userId, byte isActive) {
        return userRepository.countByUserIdAndIsActive(userId, isActive);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public UserDto create(User resources) {
        if (userRepository.findByUserName(resources.getUserName()) != null) {
            throw new EntityExistException(User.class, "username", resources.getUserName());
        }

        if (userRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", resources.getEmail());
        }
        return userMapper.toDto(userRepository.save(resources));
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public User createUserAndDept(UserDto resources) {
        Dept dept = deptRepository.save(resources.getDept());
        resources.setDeptId(dept.getId());
        return userRepository.save(toEntity(resources));
    }

    //    @Override
//    @CacheEvict(allEntries = true)
//    @Transactional(rollbackFor = Exception.class)
//    public void update(User resources) {
//        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
//        ValidationUtil.isNull(user.getId(),"User","id",resources.getId());
//        User user1 = userRepository.findByUsername(user.getUsername());
//        User user2 = userRepository.findByEmail(user.getEmail());
//
//        if(user1 !=null&&!user.getId().equals(user1.getId())){
//            throw new EntityExistException(User.class,"username",resources.getUsername());
//        }
//
//        if(user2!=null&&!user.getId().equals(user2.getId())){
//            throw new EntityExistException(User.class,"email",resources.getEmail());
//        }
//
//        // 如果用户的角色改变了，需要手动清理下缓存
//        if (!resources.getRoles().equals(user.getRoles())) {
//            String key = "role::loadPermissionByUser:" + user.getUsername();
//            redisUtils.del(key);
//            key = "role::findByUsers_Id:" + user.getId();
//            redisUtils.del(key);
//        }
//
//        user.setUsername(resources.getUsername());
//        user.setEmail(resources.getEmail());
//        user.setEnabled(resources.getEnabled());
//        user.setRoles(resources.getRoles());
//        user.setDept(resources.getDept());
//        user.setJob(resources.getJob());
//        user.setPhone(resources.getPhone());
//        user.setNickName(resources.getNickName());
//        user.setSex(resources.getSex());
//        userRepository.save(user);
//    }
//
//    @Override
//    @CacheEvict(allEntries = true)
//    @Transactional(rollbackFor = Exception.class)
//    public void updateCenter(User resources) {
//        User user = userRepository.findById(resources.getId()).orElseGet(User::new);
//        user.setNickName(resources.getNickName());
//        user.setPhone(resources.getPhone());
//        user.setSex(resources.getSex());
//        userRepository.save(user);
//    }
//
//    @Override
//    @CacheEvict(allEntries = true)
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Set<Long> ids) {
//        for (Long id : ids) {
//            userRepository.deleteById(id);
//        }
//    }
//
    @Override
    @Cacheable(key = "'loadUserByUsername:'+#p0")
    public UserDto findByName(String username) {
        User user;
        user = userRepository.findByUserName(username);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", username);
        } else {
            return toDto(user);
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void updatePass(String username, String pass) {
        userRepository.updatePass(username, pass, DateUtils.currentTime());
    }

//    @Override
//    @CacheEvict(allEntries = true)
//    @Transactional(rollbackFor = Exception.class)
//    public void updateAvatar(MultipartFile multipartFile) {
//        User user = userRepository.findByUsername(SecurityUtils.getUsername());
//        UserAvatar userAvatar = user.getUserAvatar();
//        String oldPath = "";
//        if(userAvatar != null){
//           oldPath = userAvatar.getPath();
//        }
//        File file = FileUtil.upload(multipartFile, avatar);
//        assert file != null;
//        userAvatar = userAvatarRepository.save(new UserAvatar(userAvatar,file.getName(), file.getPath(), FileUtil.getSize(multipartFile.getSize())));
//        user.setUserAvatar(userAvatar);
//        userRepository.save(user);
//        if(StringUtils.isNotBlank(oldPath)){
//            FileUtil.del(oldPath);
//        }
//    }
//
//    @Override
//    @CacheEvict(allEntries = true)
//    @Transactional(rollbackFor = Exception.class)
//    public void updateEmail(String username, String email) {
//        userRepository.updateEmail(username,email);
//    }

//    @Override
//    public void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (UserDto userDTO : queryAll) {
//            List<String> roles = userDTO.getRoles().stream().map(RoleSmallDto::getName).collect(Collectors.toList());
//            Map<String,Object> map = new LinkedHashMap<>();
//            map.put("用户名", userDTO.getUsername());
//            map.put("头像", userDTO.getAvatar());
//            map.put("邮箱", userDTO.getEmail());
//            map.put("状态", userDTO.getEnabled() ? "启用" : "禁用");
//            map.put("手机号码", userDTO.getPhone());
//            map.put("角色", roles);
//            map.put("部门", userDTO.getDept().getName());
//            map.put("岗位", userDTO.getJob().getName());
//            map.put("最后修改密码的时间", userDTO.getLastPasswordResetTime());
//            map.put("创建日期", userDTO.getCreateTime());
//            list.add(map);
//        }
//        FileUtil.downloadExcel(list, response);
//    }

    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setId(dto.getId());
        user.setUserId(dto.getUserId());
        user.setUserName(dto.getUserName());
        user.setNickName(dto.getNickName());
        user.setSex(dto.getSex());
        user.setRole(dto.getRole());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setJobId(dto.getJobId());
        user.setIsActive(dto.getIsActive());
        user.setPassword(dto.getPassword());
        user.setClient(dto.getClient());
        user.setDept(dto.getDept());
        user.setCreatedBy(dto.getCreatedBy());
        user.setCreatedDate(dto.getCreatedDate());
        user.setModifiedBy(dto.getModifiedBy());
        user.setModifiedDate(dto.getModifiedDate());

        return user;
    }

    public List<User> toEntity(List<UserDto> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<User> list = new ArrayList<User>(dtoList.size());
        for (UserDto userDto : dtoList) {
            list.add(toEntity(userDto));
        }

        return list;
    }

    public List<UserDto> toDto(List<User> entityList) {
        if (entityList == null) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>(entityList.size());
        for (User user : entityList) {
            list.add(toDto(user));
        }

        return list;
    }

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setNickName(user.getNickName());
        userDto.setSex(user.getSex());
        userDto.setRole(user.getRole());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setJobId(user.getJobId());
        userDto.setClient(user.getClient());
        userDto.setDept(user.getDept());
        userDto.setIsActive(user.getIsActive());
        userDto.setPassword(user.getPassword());
        userDto.setCreatedBy(user.getCreatedBy());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setModifiedBy(user.getModifiedBy());
        userDto.setModifiedDate(user.getModifiedDate());

        return userDto;
    }
}
