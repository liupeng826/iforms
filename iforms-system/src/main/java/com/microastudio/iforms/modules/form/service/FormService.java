package com.microastudio.iforms.modules.form.service;

import com.microastudio.iforms.modules.form.domain.Answer;
import com.microastudio.iforms.modules.form.domain.Form;
import com.microastudio.iforms.modules.form.domain.Language;
import com.microastudio.iforms.modules.form.domain.QuestionType;
import com.microastudio.iforms.modules.form.dto.AnswerDto;
import com.microastudio.iforms.modules.form.dto.FormDto;
import com.microastudio.iforms.modules.form.dto.QuestionnaireStatisticsDto;

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

    String getClient(String name, String token);

    String addAnswer(AnswerDto answers);

    List<FormDto> getForms(String clientToken, String superFormId);

    FormDto getForm(String clientToken, String id);

    List<FormDto> getFormsByUserId(String clientToken, String userId);

    List<FormDto> getFormsByDeptAndMarket(String clientToken, String deptId, String marketId);

    List<FormDto> getFormsByDeptId(String clientToken, String deptId);

    List<FormDto> getFormsByMarketId(String clientToken, String parentId);

    FormDto getAnswersWithFormByAnswerId(String clientToken, String answerId);

    List<Answer> getAnswersByAnswerId(String clientToken, String answerId);

    List<FormDto> getAnswers(String clientToken, Integer formId, String marketId, String dealerId, String month, String from, String to);

    List<QuestionnaireStatisticsDto> getQuestionnaireStatistics(Integer formId, String marketId, String dealerId, String month, String from, String to);
}
