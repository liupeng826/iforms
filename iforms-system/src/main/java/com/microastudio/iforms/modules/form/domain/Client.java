package com.microastudio.iforms.modules.form.domain;

import java.io.Serializable;

/**
 * @author peng
 */
public class Client implements Serializable {

    private static final long serialVersionUID = 5368665276515547587L;

    private Long id;
    private String name;
    private String token;
    private byte isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
