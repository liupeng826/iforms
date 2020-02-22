package com.microastudio.iforms.modules.form.dto;

import java.io.Serializable;

/**
 * @author peng
 */
public class FormRequestDto implements Serializable {

    private static final long serialVersionUID = 6813986649917909511L;

    private String clientName;
    private String clientToken;
    private String supperId;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getSupperId() {
        return supperId;
    }

    public void setSupperId(String supperId) {
        this.supperId = supperId;
    }
}
