package com.microastudio.iforms.modules.system.dto;

import com.microastudio.iforms.common.annotation.Query;
import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class UserQueryCriteria implements Serializable {

    @Query
    private Long id;

    @Query(blurry = "userId,email,userName,nickName")
    private String blurry;
}
