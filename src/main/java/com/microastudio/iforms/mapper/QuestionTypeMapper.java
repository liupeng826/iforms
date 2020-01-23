package com.microastudio.iforms.mapper;

import com.microastudio.iforms.entity.QuestionType;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionTypeMapper {

    List<QuestionType> selectQuestionType();
}
