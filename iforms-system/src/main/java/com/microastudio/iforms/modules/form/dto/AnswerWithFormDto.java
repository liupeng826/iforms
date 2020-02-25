package com.microastudio.iforms.modules.form.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author peng
 */
public class AnswerWithFormDto implements Serializable {

    private static final long serialVersionUID = 6813986649917909511L;

    private FormDto form;
    private List<AnswerDto> answers;

    public AnswerWithFormDto(FormDto form, List<AnswerDto> answers) {
        this.form = form;
        this.answers = answers;
    }

    public FormDto getForm() {
        return form;
    }

    public void setForm(FormDto form) {
        this.form = form;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
