package com.microastudio.iforms.modules.form.dto;

import java.io.Serializable;

/**
 * @author peng
 */
public class QuestionnaireStatisticsDto implements Serializable {

    private static final long serialVersionUID = 8590079970763807680L;
    private String superFormId;
    private String formTitle;
    private String optionQuestionId;
    private String questionTypeId;
    private String optionDescription;
    private String answerValue;
    private String totalNumber;
    private String netPromoterFrom;
    private String netPromoterTo;
    private String month;

    public String getSuperFormId() {
        return superFormId;
    }

    public void setSuperFormId(String superFormId) {
        this.superFormId = superFormId;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getOptionQuestionId() {
        return optionQuestionId;
    }

    public void setOptionQuestionId(String optionQuestionId) {
        this.optionQuestionId = optionQuestionId;
    }

    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
