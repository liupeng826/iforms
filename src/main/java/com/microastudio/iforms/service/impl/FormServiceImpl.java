package com.microastudio.iforms.service.impl;

import com.microastudio.iforms.entity.QuestionType;
import com.microastudio.iforms.mapper.QuestionTypeMapper;
import com.microastudio.iforms.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private QuestionTypeMapper questionTypeMapper;


    @Override
    public List<QuestionType> getQuestionType() {
        // 先查缓存，缓存没有在查库
//        QuestionType questionType = (QuestionType) redisTemplate.opsForValue().get(CommonConstants.USER_INFO_KEY+id);
        List<QuestionType> questionType = null;
        if(questionType == null) {
            questionType = questionTypeMapper.selectQuestionType();
            // 用户信息记录到缓存
//            redisTemplate.opsForValue().set(CommonConstants.USER_INFO_KEY+id, user, 30, TimeUnit.MINUTES);
        }
        return questionType;
    }

}
