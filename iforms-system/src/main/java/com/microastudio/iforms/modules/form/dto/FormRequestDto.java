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
     * form super id
     */
    private String superFormId;
    /**
     * answer id
     */
    private String answerId;
    private String deptId;
    private String marketId;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSuperFormId() {
        return superFormId;
    }

    public void setSuperFormId(String superFormId) {
        this.superFormId = superFormId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }
}
