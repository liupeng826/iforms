package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
public class SystemToken implements Serializable {

    private static final long serialVersionUID = 5368665276515547587L;

    private Long id;
    private String description;
    private String token;
    private byte isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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