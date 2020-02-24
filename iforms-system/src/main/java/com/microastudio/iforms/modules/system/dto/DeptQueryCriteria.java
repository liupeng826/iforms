package com.microastudio.iforms.modules.system.dto;

import com.microastudio.iforms.common.annotation.Query;
import lombok.Data;

import java.util.Set;

/**
* @author peng
*/
@Data
public class DeptQueryCriteria{

    @Query(type = Query.Type.IN, propName="id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;
}
