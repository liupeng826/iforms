package com.microastudio.iforms.modules.form.mapper;

import com.microastudio.iforms.modules.form.domain.*;
import com.microastudio.iforms.modules.form.dto.FormDto;
import com.microastudio.iforms.modules.form.dto.QuestionnaireStatisticsDto;

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

    int updateQuestion(Question question);

    int updateQuestionOption(QuestionOption questionOption);

    int updateSection(Section section);

    int updateForm(Form form);

    int updateFormStatus(String superFormId);

    String selectClient(String name, String token);

    List<FormDto> selectAllFormsByKey(String clientToken, String superFormId, String inc);

    FormDto selectFormById(String clientToken, String id, String inc);

    List<FormDto> selectAllFormsByUserId(String clientToken, String userId, String inc);

    List<FormDto> selectAllFormsByDeptAndMarket(String clientToken, String deptId, String marketId, String inc);

    List<FormDto> selectAllFormsByDept(String clientToken, String deptId, String inc);

    List<FormDto> selectAllFormsByMarket(String clientToken, String marketId, String inc);

    FormDto selectAnswersWithFormByAnswerId(String clientToken, String answerId);

    List<Answer> selectAnswersByAnswerId(String clientToken, String answerId);

    List<FormDto> selectAnswers(String clientToken, Integer formId, String marketId, String deptId, String month, String from, String to);

    List<QuestionnaireStatisticsDto> selectQuestionnaireOptionStatistics(String superFormId, String marketId, String deptId, String month, String from, String to);

    List<QuestionnaireStatisticsDto> selectQuestionnaireStatistics(Integer formId, String marketId, String deptId, String from, String to);

    int insertAnswer(List<Answer> answers);

    int insertCustomer(Customer customer);

    Email selectMailByTypeAndLanguage(String type, String language);
}
