package com.microastudio.iforms.modules.system.repository;

import com.microastudio.iforms.modules.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

/**
 * @author peng
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return /
     */
    User findByUserName(String username);

    /**
     * 根据邮箱查询
     *
     * @param email 邮箱
     * @return /
     */
    User findByEmail(String email);

    /**
     * 修改密码
     *
     * @param username              用户名
     * @param pass                  密码
     * @param modifiedDate
     */
    @Modifying
    @Query(value = "update user set password = ?2 , modified_date = ?3 where user_name = ?1", nativeQuery = true)
    void updatePass(String username, String pass, Timestamp modifiedDate);

    /**
     * 修改邮箱
     *
     * @param username 用户名
     * @param email    邮箱
     */
    @Modifying
    @Query(value = "update user set email = ?2 where user_name = ?1", nativeQuery = true)
    void updateEmail(String username, String email);
}
