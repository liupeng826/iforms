package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peng
 */
@Data
public class QuestionOption implements Serializable {

    private static final long serialVersionUID = 948689731410646125L;

    private Long id;
    private Long questionId;
    private String description;
    private int sequence;
    private String totalValue;
    private byte isActive;
    private String language;
}
