package com.microastudio.iforms.service.impl;

import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.entity.QuestionType;
import com.microastudio.iforms.mapper.QuestionTypeMapper;
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
    private QuestionTypeMapper questionTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<QuestionType> getQuestionType() {
        // 先查缓存，缓存没有在查库
        List<QuestionType> questionType = (List<QuestionType>) redisTemplate.opsForValue().get(CommonConstants.QUESTION_TYPE_KEY);
        if (questionType == null) {
            questionType = questionTypeMapper.selectQuestionType();
            // 信息记录到缓存
            redisTemplate.opsForValue().set(CommonConstants.QUESTION_TYPE_KEY, questionType, 30, TimeUnit.MINUTES);
        }
        return questionType;
    }

}
