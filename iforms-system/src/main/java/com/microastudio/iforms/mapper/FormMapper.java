package com.microastudio.iforms.mapper;

import com.microastudio.iforms.domain.Language;
import com.microastudio.iforms.domain.QuestionType;

import java.util.List;

/**
 * @author peng
 */
public interface FormMapper {

    List<QuestionType> selectQuestionType();

    List<Language> selectLanguage();
}
