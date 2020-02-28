package com.microastudio.iforms.modules.system.service;


import com.microastudio.iforms.modules.system.domain.User;
import com.microastudio.iforms.modules.system.dto.UserDto;
import com.microastudio.iforms.modules.system.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author peng
 */
public interface UserService {

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return /
     */
    UserDto findById(long id);

    UserDto findByUserId(String id);

    long countByUserIdAndIsActive(String userId, byte isActive);

    User createUserAndDept(UserDto resources);

    /**
     * 新增用户
     *
     * @param resources /
     * @return /
     */
    UserDto create(User resources);

//    /**
//     * 编辑用户
//     * @param resources /
//     */
//    void update(User resources);
//
//    /**
//     * 删除用户
//     * @param ids /
//     */
//    void delete(Set<Long> ids);
//

    /**
     * 根据用户名查询
     *
     * @param username /
     * @return /
     */
    UserDto findByName(String username);

    /**
     * 修改密码
     *
     * @param username        用户名
     * @param encryptPassword 密码
     */
    void updatePass(String username, String encryptPassword);

//    /**
//     * 修改头像
//     * @param file 文件
//     */
//    void updateAvatar(MultipartFile file);
//
//    /**
//     * 修改邮箱
//     * @param username 用户名
//     * @param email 邮箱
//     */
//    void updateEmail(String username, String email);
//

    /**
     * 查询全部
     *
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(UserQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部不分页
     *
     * @param criteria 条件
     * @return /
     */
    List<UserDto> queryAll(UserQueryCriteria criteria);

//    /**
//     * 导出数据
//     * @param queryAll 待导出的数据
//     * @param response /
//     * @throws IOException /
//     */
//    void download(List<UserDto> queryAll, HttpServletResponse response) throws IOException;
//
//    /**
//     * 用户自助修改资料
//     * @param resources /
//     */
//    void updateCenter(User resources);
}
