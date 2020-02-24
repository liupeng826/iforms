package com.microastudio.iforms.modules.form.dto;

import com.microastudio.iforms.modules.system.domain.Client;

import java.io.Serializable;

/**
 * @author peng
 */
public class FormRequestDto implements Serializable {

    private static final long serialVersionUID = 6813986649917909511L;

    private Client client;
    /**
     *  form supper id
     */
    private String supperId;
    /**
     * answer id
     */
    private String answerId;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSupperId() {
        return supperId;
    }

    public void setSupperId(String supperId) {
        this.supperId = supperId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
