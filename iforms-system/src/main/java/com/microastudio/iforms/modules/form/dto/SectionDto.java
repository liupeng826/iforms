package com.microastudio.iforms.modules.form.dto;

import com.microastudio.iforms.modules.form.domain.Question;

import java.io.Serializable;
import java.util.List;

/**
 * @author peng
 */
public class SectionDto implements Serializable {

    private static final long serialVersionUID = -1944382737578744212L;

    private Long id;
    private String superSectionId;
    private Long formId;
    private String title;
    private String description;
    private Integer sequence;
    private byte isActive;
    List<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuperSectionId() {
        return superSectionId;
    }

    public void setSuperSectionId(String superSectionId) {
        this.superSectionId = superSectionId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
