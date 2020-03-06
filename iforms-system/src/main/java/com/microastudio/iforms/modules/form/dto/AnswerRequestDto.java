package com.microastudio.iforms.modules.form.dto;

import com.microastudio.iforms.modules.system.domain.Client;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
public class AnswerRequestDto implements Serializable {

    private static final long serialVersionUID = -788672281107006955L;

    private Client client;
    private Integer formId;
    private String marketId;
    private String dealerId;
    private Integer month;
    private Timestamp from;
    private Timestamp to;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }
}
