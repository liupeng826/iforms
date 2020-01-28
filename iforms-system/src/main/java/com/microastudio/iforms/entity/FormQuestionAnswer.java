package com.microastudio.iforms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
public class FormQuestionAnswer implements Serializable {

    private static final long serialVersionUID = -3538270732034929962L;

    private Long id;
    private Long formId;
    private Long questionId;
    private String answerDescription;
    private Long answerOptionId;
    private String answerValue;
    private String totalValue;
    private String reference;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
}
