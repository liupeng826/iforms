package com.microastudio.iforms.modules.system.dto;

import com.microastudio.iforms.common.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    private String value;

    @Query(blurry = "email,userName,nickName")
    private String keyword;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
