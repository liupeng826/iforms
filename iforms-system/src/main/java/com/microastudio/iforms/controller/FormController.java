package com.microastudio.iforms.controller;

import com.alibaba.fastjson.JSONObject;
import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.dto.FormDto;
import com.microastudio.iforms.entity.Language;
import com.microastudio.iforms.entity.QuestionType;
import com.microastudio.iforms.service.FormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author peng
 * @date 2020/01/23
 */
@RestController
@RequestMapping("/api/form")
public class FormController {
    private final static Logger logger = LoggerFactory.getLogger(FormController.class);

    @Autowired
    private FormService formService;

    @GetMapping("/questionType")
    public ResultResponse getQuestionType() {
        logger.info("getQuestionType");
        ResultResponse resultResponse = new ResultResponse();
        try {
            List<QuestionType> questionType = formService.getQuestionType();
            return ResultResponse.success(questionType);
        } catch (Exception e) {
            logger.error("getQuestionType异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @GetMapping("/language")
    public ResultResponse getLanguage() {
        logger.info("getLanguage");
        ResultResponse resultResponse = new ResultResponse();
        try {
            List<Language> languages = formService.getLanguage();
            return ResultResponse.success(languages);
        } catch (Exception e) {
            logger.error("getLanguage异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/form")
    public ResultResponse generateForm(@RequestBody FormDto formParam) {
        logger.info("generateForm");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if(formParam == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("generateForm入参："+ JSONObject.toJSONString(formParam));

            List<Language> languages = formService.getLanguage();
            return ResultResponse.success(languages);
        } catch (Exception e) {
            logger.error("generateForm异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

}
