package com.microastudio.iforms.service;

import com.microastudio.iforms.entity.Language;
import com.microastudio.iforms.entity.QuestionType;

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

}
