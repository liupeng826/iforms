package com.microastudio.iforms.modules.form.domain;

import java.io.Serializable;

/**
 * @author peng
 */
public class QuestionType implements Serializable {

    private static final long serialVersionUID = -3539414648435619163L;

    private Long id;
    private String description;
    private byte isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }
}