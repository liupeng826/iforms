package com.microastudio.iforms.service.impl;

import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.entity.Language;
import com.microastudio.iforms.entity.QuestionType;
import com.microastudio.iforms.mapper.FormMapper;
import com.microastudio.iforms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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

}
