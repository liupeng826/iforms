package com.microastudio.iforms.modules.system.repository;


import com.microastudio.iforms.modules.system.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author peng
 */
@SuppressWarnings("all")
public interface DeptRepository extends JpaRepository<Dept, Long>, JpaSpecificationExecutor<Dept> {

    /**
     * 根据 PID 查询
     *
     * @param id pid
     * @return /
     */
    List<Dept> findByPid(Long id);

    /**
     * 根据ID查询名称
     *
     * @param id ID
     * @return /
     */
    @Query(value = "select name from dept where id = ?1", nativeQuery = true)
    String findNameById(Long id);

    /**
     * 根据角色ID 查询
     *
     * @param id 角色ID
     * @return /
     */
//    Set<Dept> findByRoles_Id(Long id);


    /**
     * 根据deptId和pid 查询
     *
     * @param deptId deptID
     * @param pid    PID
     * @return /
     */
    long countByDeptIdAndPidAndIsActive(String deptId, Long pid, byte isActive);
}
