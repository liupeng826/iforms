package com.microastudio.iforms.service.impl;

import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.utils.StringUtil;
import com.microastudio.iforms.domain.*;
import com.microastudio.iforms.dto.FormDto;
import com.microastudio.iforms.dto.SectionDto;
import com.microastudio.iforms.mapper.FormMapper;
import com.microastudio.iforms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public String generateForm(FormDto formDto) {
        int rows = 0;

        Form form = new Form();
        form.setTitle(formDto.getTitle());
        form.setDescription(formDto.getDescription());
        form.setLevel(formDto.getLevel());
        form.setMarketId(formDto.getMarketId());
        form.setDealerId(formDto.getDealerId());
        form.setSystemToken(formDto.getSystemToken());
        form.setSendEmail(formDto.getSendEmail());
        form.setType(formDto.getType());
        form.setCreatedBy(formDto.getCreatedBy());
        form.setModifiedBy(formDto.getModifiedBy());
        form.setLanguage(formDto.getLanguage());

        // insert form
        formMapper.insertForm(form);
        if (rows == 0) {
            return null;
        }
        Long formId = form.getId();

        for (SectionDto sectionDto : formDto.getSections()) {
            Long sectionId = null;

            // insert section
            if (formDto.getIncludeSection() == 1) {
                Section section = new Section();
                section.setSequence(sectionDto.getSequence());
                section.setDescription(sectionDto.getDescription());

                rows = formMapper.insertSection(section);
                if (rows == 0) {
                    return null;
                }
                sectionId = section.getId();
            }

            // insert Question
            for (Question q : sectionDto.getQuestions()) {
                rows = formMapper.insertQuestion(q);
                if (rows == 0) {
                    return null;
                }

                Long questionId = q.getId();

                // insert FormQuestionMapping
                FormQuestionMapping fm = new FormQuestionMapping();
                fm.setFormId(formId);
                fm.setSectionId(sectionId);
                fm.setQuestionId(questionId);
                fm.setLanguage(q.getLanguage());
                fm.setSupperId(StringUtil.getUuid());

                rows = formMapper.insertFormQuestionMapping(fm);
                if (rows == 0) {
                    return null;
                }

                // insert QuestionOption
                for (QuestionOption qo : q.getQuestionOptions()) {
                    qo.setQuestionId(questionId);
                    formMapper.insertQuestionOption(qo);
                }
            }
        }

        return formId.toString();
    }

    @Override
    public String getSystemToken(String key) {
        return formMapper.selectSystemToken(key);
    }

}
