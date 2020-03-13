package com.microastudio.iforms.modules.form.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
public class Question implements Serializable {

    private static final long serialVersionUID = -6756265354805158478L;

    private Long id;
    private String superQuestionId;
    private Long sectionId;
    private String title;
    private String subtitle;
    private Long questionTypeId;
    private byte isActive;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String language;
    private byte mandatory;
    private int sequence;

    List<QuestionOption> questionOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuperQuestionId() {
        return superQuestionId;
    }

    public void setSuperQuestionId(String superQuestionId) {
        this.superQuestionId = superQuestionId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public byte getMandatory() {
        return mandatory;
    }

    public void setMandatory(byte mandatory) {
        this.mandatory = mandatory;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
