package com.microastudio.iforms.modules.form.dto;

import java.io.Serializable;

/**
 * @author peng
 */
public class FormRequestDto implements Serializable {

    private static final long serialVersionUID = 6813986649917909511L;

    private String description;
    private String token;
    private String supperId;

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

    public String getSupperId() {
        return supperId;
    }

    public void setSupperId(String supperId) {
        this.supperId = supperId;
    }
}