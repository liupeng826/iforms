package com.microastudio.iforms.service;

import com.microastudio.iforms.domain.Form;
import com.microastudio.iforms.domain.FormQuestionAnswer;
import com.microastudio.iforms.domain.Language;
import com.microastudio.iforms.domain.QuestionType;
import com.microastudio.iforms.dto.AnswerDto;
import com.microastudio.iforms.dto.FormDto;

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
