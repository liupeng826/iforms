package com.microastudio.iforms.modules.form.service;

import com.microastudio.iforms.modules.form.domain.Answer;
import com.microastudio.iforms.modules.form.dto.FormDto;
import com.microastudio.iforms.modules.form.domain.Form;
import com.microastudio.iforms.modules.form.domain.Language;
import com.microastudio.iforms.modules.form.domain.QuestionType;
import com.microastudio.iforms.modules.form.dto.AnswerDto;

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

    List<FormDto> getFormsByDeptId(String clientToken, String deptId);

    List<FormDto> getFormsByMarketId(String clientToken, String parentId);

    List<Answer> getAllAnswers(String clientToken, String superFormId);
}
