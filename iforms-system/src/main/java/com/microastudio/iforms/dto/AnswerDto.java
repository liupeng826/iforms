package com.microastudio.iforms.dto;

import com.microastudio.iforms.domain.FormQuestionAnswer;
import com.microastudio.iforms.domain.SystemToken;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author peng
 */
@Data
public class AnswerDto implements Serializable {

    private static final long serialVersionUID = -3538270732034929962L;

    private SystemToken systemToken;
    private Long formId;
    private String reference;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private List<FormQuestionAnswer> answers;

//    private class Answer {
//        private Long questionId;
//        private String answerDescription;
//        private Long answerOptionId;
//        private String answerValue;
//        private String totalValue;
//    }
}
