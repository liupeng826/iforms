package com.microastudio.iforms.modules.system.dto;

import lombok.Data;
import com.microastudio.iforms.common.annotation.Query;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @author peng
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    @Query(blurry = "email,username,nickName")
    private String blurry;

    @Query
    private byte isActive;

    private Long deptId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
