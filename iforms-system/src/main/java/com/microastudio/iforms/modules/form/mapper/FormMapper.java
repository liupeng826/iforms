package com.microastudio.iforms.modules.form.mapper;

import com.microastudio.iforms.modules.form.domain.*;
import com.microastudio.iforms.modules.form.dto.FormDto;

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

    String selectClient(String name, String token);

    List<FormDto> selectAllFormsByKey(String clientToken, String superFormId);

    FormDto selectFormById(String clientToken, String id);

    List<FormDto> selectAllFormsByUserId(String clientToken, String userId);

    List<FormDto> selectAllFormsByDept(String clientToken, String deptId);

    List<FormDto> selectAllFormsByMarket(String clientToken, String marketId);

    List<Answer> selectAllAnswersByKey(String clientToken, String answerId);

    int insertAnswer(List<Answer> answers);

    int insertCustomer(Customer customer);

}
