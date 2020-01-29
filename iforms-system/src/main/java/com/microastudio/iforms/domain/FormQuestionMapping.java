package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class FormQuestionMapping implements Serializable {

    private static final long serialVersionUID = 8058774662775679698L;

    private String supperId;
    private Long id;
    private Long formId;
    private Long sectionId;
    private Long questionId;
    private String language;
}
