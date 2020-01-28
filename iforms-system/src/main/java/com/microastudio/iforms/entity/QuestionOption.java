package com.microastudio.iforms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author peng
 */
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
