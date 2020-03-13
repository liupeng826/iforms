package com.microastudio.iforms.modules.form.domain;

import java.io.Serializable;

/**
 * @author peng
 */
public class QuestionOption implements Serializable {

    private static final long serialVersionUID = 948689731410646125L;

    private Long id;
    private String superOptionId;
    private Long questionId;
    private String description;
    private int sequence;
    private int totalValue;
    private String netPromoterFrom;
    private String netPromoterTo;
    private String language;
    private byte isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuperOptionId() {
        return superOptionId;
    }

    public void setSuperOptionId(String superOptionId) {
        this.superOptionId = superOptionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public String getNetPromoterFrom() {
        return netPromoterFrom;
    }

    public void setNetPromoterFrom(String netPromoterFrom) {
        this.netPromoterFrom = netPromoterFrom;
    }

    public String getNetPromoterTo() {
        return netPromoterTo;
    }

    public void setNetPromoterTo(String netPromoterTo) {
        this.netPromoterTo = netPromoterTo;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}
