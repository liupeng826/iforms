package com.microastudio.iforms.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
@Data
public class Question implements Serializable {

    private static final long serialVersionUID = -6756265354805158478L;

    private Long id;
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
}
