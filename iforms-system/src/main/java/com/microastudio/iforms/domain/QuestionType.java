package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class QuestionType implements Serializable {

    private static final long serialVersionUID = -3539414648435619163L;

    private Long id;
    private String description;
    private byte isActive;
}