package com.microastudio.iforms.modules.form.dto;

import com.microastudio.iforms.modules.form.domain.Answer;

import java.io.Serializable;
import java.util.List;

/**
 * @author peng
 */
public class AnswerWithFormDto implements Serializable {

    private static final long serialVersionUID = 6813986649917909511L;

    private FormDto form;
    private List<Answer> answers;

    public AnswerWithFormDto(FormDto form, List<Answer> answers) {
        this.form = form;
        this.answers = answers;
    }

    public FormDto getForm() {
        return form;
    }

    public void setForm(FormDto form) {
        this.form = form;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
