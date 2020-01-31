package com.microastudio.iforms.controller;

import com.alibaba.fastjson.JSONObject;
import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.domain.Language;
import com.microastudio.iforms.domain.QuestionType;
import com.microastudio.iforms.domain.SystemToken;
import com.microastudio.iforms.dto.AnswerDto;
import com.microastudio.iforms.dto.FormDto;
import com.microastudio.iforms.service.FormService;
import org.apache.commons.lang3.StringUtils;
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

    @GetMapping("/validateToken")
    public ResultResponse validateToken(@RequestParam(value = "key") String key
            , @RequestParam(value = "systemToken") String systemToken) {
        return getToken(key, systemToken);
    }

    private ResultResponse getToken(String key, String systemToken) {
        logger.info("getToken");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(systemToken)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getToken入参：" + key + "," + systemToken);

            String token = formService.getSystemToken(key);
            if (!systemToken.equals(token)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_AUTH_TOKEN, CommonConstants.ERRORS_MSG_AUTH_TOKEN);
            }

            return ResultResponse.success("");
        } catch (Exception e) {
            logger.error("getToken异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/getAllForms")
    public ResultResponse getAllForms(@RequestBody SystemToken systemToken) {
        logger.info("getAllForms");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (systemToken == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getAllForms入参：" + JSONObject.toJSONString(systemToken));

            // validateToken
            resultResponse = getToken(systemToken.getDescription(), systemToken.getToken());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            // get form
            List<FormDto> forms = formService.getAllForms(systemToken.getToken());

            return ResultResponse.success(forms);
        } catch (Exception e) {
            logger.error("getAllForms异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/generateForm")
    public ResultResponse generateForm(@RequestBody FormDto formParam) {
        logger.info("generateForm");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (formParam == null || formParam.getSections() == null
                    || formParam.getSections().get(0).getQuestions() == null
                    || formParam.getSystemToken() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("generateForm入参：" + JSONObject.toJSONString(formParam));

            // validateToken
            resultResponse = getToken(formParam.getSystemToken().getDescription(), formParam.getSystemToken().getToken());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            // generate form
            String supperId = formService.generateForm(formParam);

            if (supperId == null) {
                logger.error("generateForm异常：supperId is null");
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
            }

            return ResultResponse.success(supperId);
        } catch (Exception e) {
            logger.error("generateForm异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/answer")
    public ResultResponse answer(@RequestBody AnswerDto answerDto) {
        logger.info("answer");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (answerDto == null || answerDto.getSystemToken() == null
                    || answerDto.getAnswers() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("answer入参：" + JSONObject.toJSONString(answerDto));

            // validateToken
            resultResponse = getToken(answerDto.getSystemToken().getDescription(), answerDto.getSystemToken().getToken());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            // add answer
            int rows = formService.addAnswer(answerDto);

            if (rows == 0) {
                logger.error("answer插入异常");
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
            }

            return ResultResponse.success("");
        } catch (Exception e) {
            logger.error("answer异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

}
