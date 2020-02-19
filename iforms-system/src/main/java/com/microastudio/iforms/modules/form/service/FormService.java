package com.microastudio.iforms.modules.form.service;

import com.microastudio.iforms.modules.form.dto.FormDto;
import com.microastudio.iforms.modules.form.domain.Form;
import com.microastudio.iforms.modules.form.domain.Language;
import com.microastudio.iforms.modules.form.domain.QuestionType;
import com.microastudio.iforms.modules.form.dto.AnswerDto;

import java.util.List;

public interface FormService {
    /**
     * get all question type
     *
     * @param
     * @return
     */
    List<QuestionType> getQuestionType();
    List<Language> getLanguage();
    Form generateForm(FormDto formDto);
    String getSystemToken(String key);
    int addAnswer(AnswerDto answers);
    List<FormDto> getAllForms(String key, String supperId);
}