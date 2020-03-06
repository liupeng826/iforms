package com.microastudio.iforms.modules.form.service.impl;

import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.utils.StringUtils;
import com.microastudio.iforms.modules.form.domain.*;
import com.microastudio.iforms.modules.form.dto.AnswerDto;
import com.microastudio.iforms.modules.form.dto.FormDto;
import com.microastudio.iforms.modules.form.dto.SectionDto;
import com.microastudio.iforms.modules.form.mapper.FormMapper;
import com.microastudio.iforms.modules.form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author peng
 */
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormMapper formMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<QuestionType> getQuestionType() {
        // 先查缓存，缓存没有在查库
        List<QuestionType> questionTypes = (List<QuestionType>) redisTemplate.opsForValue().get(CommonConstants.QUESTION_TYPE_KEY);
        if (questionTypes == null) {
            questionTypes = formMapper.selectQuestionType();
            // 信息记录到缓存
            redisTemplate.opsForValue().set(CommonConstants.QUESTION_TYPE_KEY, questionTypes, 30, TimeUnit.MINUTES);
        }
        return questionTypes;
    }

    @Override
    public List<Language> getLanguage() {
        // 先查缓存，缓存没有在查库
        List<Language> languages = (List<Language>) redisTemplate.opsForValue().get(CommonConstants.QUESTION_TYPE_KEY);
        if (languages == null) {
            languages = formMapper.selectLanguage();
            // 信息记录到缓存
            redisTemplate.opsForValue().set(CommonConstants.QUESTION_TYPE_KEY, languages, 30, TimeUnit.MINUTES);
        }
        return languages;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Form generateForm(FormDto formDto) {

        int rows = 0;
        String uuid;

        Form form = new Form();
        if (StringUtils.isEmpty(formDto.getSuperFormId())) {
            uuid = StringUtils.getUuid();
        } else {
            uuid = formDto.getSuperFormId();
        }

        form.setSuperFormId(uuid);
        form.setTitle(formDto.getTitle());
        form.setDescription(formDto.getDescription());
        form.setLevel(formDto.getLevel());
        form.setMarketId(formDto.getMarketId());
        form.setDeptId(formDto.getDeptId());
        form.setClient(formDto.getClient().getToken());
        form.setSendEmail(formDto.getSendEmail());
        form.setType(formDto.getType());
        form.setCreatedBy(formDto.getCreatedBy());
        form.setModifiedBy(formDto.getModifiedBy());
        form.setLanguage(formDto.getLanguage());
        form.setIncludeSection(formDto.getIncludeSection());

        // insert form
        rows = formMapper.insertForm(form);
        if (rows <= 0) {
            return null;
        }

        Long formId = form.getId();

        for (SectionDto sectionDto : formDto.getSections()) {
            Long sectionId = null;

            // insert section
            Section section = new Section();
            section.setFormId(formId);
            section.setTitle(sectionDto.getTitle());
            section.setDescription(sectionDto.getDescription());
            if (formDto.getIncludeSection() == 1) {
                section.setSequence(0);
            } else {
                section.setSequence(sectionDto.getSequence());
            }

            section.setLanguage(formDto.getLanguage());

            rows = formMapper.insertSection(section);
            if (rows <= 0) {
                return null;
            }
            sectionId = section.getId();

            // insert Question
            for (Question q : sectionDto.getQuestions()) {
                q.setSectionId(sectionId);
                q.setLanguage(formDto.getLanguage());
                q.setCreatedBy(formDto.getCreatedBy());
                q.setModifiedBy(formDto.getModifiedBy());

                rows = formMapper.insertQuestion(q);
                if (rows <= 0) {
                    return null;
                }

                Long questionId = q.getId();

//                // insert FormQuestionMapping
//                FormQuestionMapping fm = new FormQuestionMapping();
//                fm.setFormId(formId);
//                fm.setSectionId(sectionId);
//                fm.setQuestionId(questionId);
//                fm.setLanguage(q.getLanguage());
//                fm.setSuperFormId(superFormId);
//
//                rows = formMapper.insertFormQuestionMapping(fm);
//                if (rows <= 0) {
//                    return null;
//                }

                // insert QuestionOption
                if (q.getQuestionOptions() != null) {
                    for (QuestionOption qo : q.getQuestionOptions()) {
                        qo.setQuestionId(questionId);
                        qo.setLanguage(formDto.getLanguage());

                        formMapper.insertQuestionOption(qo);
                    }
                }
            }
        }

        return form;
    }

    @Override
    public String getClient(String name, String token) {
        return formMapper.selectClient(name, token);
    }

    @Override
    public List<FormDto> getForms(String clientToken, String superFormId) {
        return formMapper.selectAllFormsByKey(clientToken, superFormId);
    }

    @Override
    public FormDto getForm(String clientToken, String id) {
        return formMapper.selectFormById(clientToken, id);
    }

    @Override
    public List<FormDto> getFormsByUserId(String clientToken, String userId) {
        return formMapper.selectAllFormsByUserId(clientToken, userId);
    }

    @Override
    public List<FormDto> getFormsByDeptAndMarket(String clientToken, String deptId, String marketId) {
        return formMapper.selectAllFormsByDeptAndMarket(clientToken, deptId, marketId);
    }

    @Override
    public List<FormDto> getFormsByDeptId(String clientToken, String deptId) {
        return formMapper.selectAllFormsByDept(clientToken, deptId);
    }

    @Override
    public List<FormDto> getFormsByMarketId(String clientToken, String parentId) {
        return formMapper.selectAllFormsByMarket(clientToken, parentId);
    }

    @Override
    public List<Answer> getAnswersByAnswerId(String clientToken, String answerId) {
        return formMapper.selectAnswersByAnswerId(clientToken, answerId);
    }

    @Override
    public List<FormDto> getAnswers(String clientToken, Integer formId, String marketId, String deptId, String month, String from, String to) {
        return formMapper.selectAnswers(clientToken, formId, marketId, deptId, month, from, to);
    }

    @Override
    public FormDto getAnswersWithFormByAnswerId(String clientToken, String answerId) {
        return formMapper.selectAnswersWithFormByAnswerId(clientToken, answerId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addAnswer(AnswerDto answerDto) {
        int rows;
        Long customerId = null;
        Customer customer = answerDto.getCustomer();
        // insert customer
        if (customer != null) {
            rows = formMapper.insertCustomer(answerDto.getCustomer());
            if (rows > 0) {
                customerId = customer.getId();
            }
        }

        String uuid = StringUtils.getUuid();

        // insert answers
        List<Answer> answerList = new ArrayList<>();
        for (Answer answer : answerDto.getAnswers()) {
            answer.setAnswerId(uuid);
            answer.setCustomerId(customerId);
            answer.setLanguage(answerDto.getLanguage());
            answer.setFormId(answerDto.getFormId());
            answer.setReference(answerDto.getReference());
            answer.setCreatedBy(answerDto.getCreatedBy());
            answer.setModifiedBy(answerDto.getModifiedBy());
            answerList.add(answer);
        }

        formMapper.insertAnswer(answerList);
        return uuid;
    }


}
