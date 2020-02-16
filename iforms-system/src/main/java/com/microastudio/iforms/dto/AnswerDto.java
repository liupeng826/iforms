package com.microastudio.iforms.dto;

import com.microastudio.iforms.domain.Customer;
import com.microastudio.iforms.domain.FormQuestionAnswer;
import com.microastudio.iforms.domain.SystemToken;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
public class AnswerDto implements Serializable {

    private static final long serialVersionUID = -3538270732034929962L;

    private SystemToken systemToken;
    private Long formId;
    private String reference;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private List<FormQuestionAnswer> answers;
    private Customer customer;

    public SystemToken getSystemToken() {
        return systemToken;
    }

    public void setSystemToken(SystemToken systemToken) {
        this.systemToken = systemToken;
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

    public List<FormQuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<FormQuestionAnswer> answers) {
        this.answers = answers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
