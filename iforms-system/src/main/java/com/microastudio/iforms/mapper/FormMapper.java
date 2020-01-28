package com.microastudio.iforms.mapper;

import com.microastudio.iforms.entity.Language;
import com.microastudio.iforms.entity.QuestionType;

import java.util.List;

/**
 * @author peng
 */
public interface FormMapper {

    List<QuestionType> selectQuestionType();

    List<Language> selectLanguage();
}
