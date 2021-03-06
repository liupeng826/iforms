package com.microastudio.iforms.modules.form.service;

import com.microastudio.iforms.modules.form.domain.*;
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

    Form addForm(FormDto formDto);

    Form updateForm(FormDto formDto);

    int updateFormStatus(String superFormId);

    String getClient(String name, String token);

    String addAnswer(AnswerDto answers);

    List<FormDto> getForms(String clientToken, String superFormId, String inc);

    FormDto getForm(String clientToken, String id, String inc);

    List<FormDto> getFormsByUserId(String clientToken, String userId, String inc);

    List<FormDto> getFormsByDeptAndMarket(String clientToken, String deptId, String marketId, String inc);

    List<FormDto> getFormsByDeptId(String clientToken, String deptId, String inc);

    List<FormDto> getFormsByMarketId(String clientToken, String parentId, String inc);

    FormDto getAnswersWithFormByAnswerId(String clientToken, String answerId);

    List<Answer> getAnswersByAnswerId(String clientToken, String answerId);

    List<FormDto> getAnswers(String clientToken, Integer formId, String marketId, String dealerId, String month, String from, String to);

    List<QuestionnaireStatisticsDto> getQuestionnaireOptionStatistics(String superFormId, String marketId, String dealerId, String month, String from, String to);

    List<QuestionnaireStatisticsDto> getQuestionnaireStatistics(Integer formId, String marketId, String dealerId, String from, String to);

    Email getMailByTypeAndLanguage(String type, String language);

    List getAnswerDataByFormId(String formId);

    List getAnswerData(String marketId);
}
