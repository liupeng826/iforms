package com.microastudio.iforms.modules.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author peng
 */
@Getter
@Setter
public class AuthUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";

    private boolean isEncrypt = true;

    @Override
    public String toString() {
        return "{username=" + username  + ", password= ******}";
    }
}
