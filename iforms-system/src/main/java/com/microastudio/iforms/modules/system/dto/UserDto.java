package com.microastudio.iforms.modules.system.dto;

import com.microastudio.iforms.modules.system.domain.Client;
import com.microastudio.iforms.modules.system.domain.Dept;
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

    private String userName;

    private String nickName;

    private byte sex;

    private int role;

    private String email;

    private String phone;

    private String jobId;

    private Client client;

    private Dept dept;

    private byte isActive;

    //    @JsonIgnore
    private String password;

    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
}
