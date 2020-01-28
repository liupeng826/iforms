package com.microastudio.iforms.entity;

import java.io.Serializable;

/**
 * @author peng
 */
public class FormQuestionMapping implements Serializable {

    private static final long serialVersionUID = 8058774662775679698L;

    private Long supperId;
    private Long id;
    private Long formId;
    private Long questionId;
    private byte isMandatory;
    private int sequence;
    private byte isActive;
    private String language;
}
