package com.microastudio.iforms.modules.system.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
@Data
public class UserDto implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String userId;

    private String userName;

    private String nickName;

    private String sex;

    private String role;

    private String email;

    private String phone;

    private String jobId;

    private String client;

    private String market;

    private String branch;

    private byte isActive;

    @JsonIgnore
    private String password;

    private Long deptId;

    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
}
