package com.microastudio.iforms.service;

import com.microastudio.iforms.domain.Language;
import com.microastudio.iforms.domain.QuestionType;
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
    String generateForm(FormDto formDto);
}
