package com.microastudio.iforms.mapper;

import com.microastudio.iforms.domain.*;
import com.microastudio.iforms.dto.AnswerDto;
import com.microastudio.iforms.dto.FormDto;

import java.util.List;

/**
 * @author peng
 */
public interface FormMapper {

    List<QuestionType> selectQuestionType();

    List<Language> selectLanguage();

    int insertQuestion(Question question);
    int insertQuestionOption(QuestionOption questionOption);
    int insertSection(Section section);
    int insertForm(Form form);
//    int insertFormQuestionMapping(FormQuestionMapping formQuestionMapping);

    String selectSystemToken(String key);
    List<FormDto> selectAllFormsByKey(String key);
    int insertAnswer(List<FormQuestionAnswer> answers);
    int insertCustomer(Customer customer);

}
