package com.microastudio.iforms.modules.form.dto;

import com.microastudio.iforms.modules.form.domain.Answer;
import com.microastudio.iforms.modules.system.domain.Client;
import com.microastudio.iforms.modules.form.domain.Customer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
public class AnswerDto implements Serializable {

    private static final long serialVersionUID = -3538270732034929962L;

    private Client client;
    private Long formId;
    private List<Answer> answers;
    private Customer customer;
    private String language;
    private String reference;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private boolean needSendEmail;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getNeedSendEmail() {
        return needSendEmail;
    }

    public void setNeedSendEmail(boolean needSendEmail) {
        this.needSendEmail = needSendEmail;
    }
}
