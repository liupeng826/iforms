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
    private int totalValue;
    private String netPromoterFrom;
    private String netPromoterTo;
    private String language;
    private byte isActive;
}
