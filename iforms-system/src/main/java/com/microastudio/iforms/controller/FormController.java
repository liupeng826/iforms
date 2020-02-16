package com.microastudio.iforms.controller;

import com.alibaba.fastjson.JSONObject;
import com.microastudio.common.bean.CommonConstants;
import com.microastudio.common.bean.ResultResponse;
import com.microastudio.iforms.domain.Form;
import com.microastudio.iforms.domain.Language;
import com.microastudio.iforms.domain.QuestionType;
import com.microastudio.iforms.dto.AnswerDto;
import com.microastudio.iforms.dto.FormDto;
import com.microastudio.iforms.dto.FormRequestDto;
import com.microastudio.iforms.service.FormService;
import com.microastudio.iforms.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private MailService mailService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @GetMapping("/questionType")
    public ResultResponse getQuestionType() {
        logger.info("getQuestionType");
        ResultResponse resultResponse = new ResultResponse();
        try {
            List<QuestionType> questionType = formService.getQuestionType();
            resultResponse.success(questionType);
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
            resultResponse.success(languages);
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

            resultResponse.success("");
        } catch (Exception e) {
            logger.error("getToken异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/getAllForms")
    public ResultResponse getAllForms(@RequestBody FormRequestDto dto) {
        logger.info("getAllForms");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (dto == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getAllForms入参：" + JSONObject.toJSONString(dto));

            // validateToken
            resultResponse = getToken(dto.getDescription(), dto.getToken());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            // get form
            List<FormDto> forms = formService.getAllForms(dto.getToken(), dto.getSupperId());

            resultResponse.success(forms);
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
            Form form = formService.generateForm(formParam);

            if (form == null) {
                logger.error("generateForm异常：supperId is null");
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
                return resultResponse;
            }

            resultResponse.success(form);
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
                return resultResponse;
            }

            resultResponse.success("");
            logger.info("answer插入成功");
        } catch (Exception e) {
            logger.error("answer插入异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }

        try {
            //send email
            logger.info("开始发送邮件");
            Map<String, Object> model = new HashMap<>();
            model.put("username", "username");
            model.put("templateType", "Freemarker");
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("emailTemplate.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mailService.sendHtmlMail("peng.liu@volvo.com", "主题：这是模板邮件", html);
            resultResponse.success("");
            logger.info("成功发送邮件");
        } catch (IOException | TemplateException e) {
            logger.error("发送邮件异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_MAIL);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_MAIL);
        }

        return resultResponse;
    }

}
