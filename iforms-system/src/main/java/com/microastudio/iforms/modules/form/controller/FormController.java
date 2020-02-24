package com.microastudio.iforms.modules.form.controller;

import com.alibaba.fastjson.JSONObject;
import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.modules.form.service.MailService;
import com.microastudio.iforms.modules.form.domain.Form;
import com.microastudio.iforms.modules.form.domain.Language;
import com.microastudio.iforms.modules.form.domain.QuestionType;
import com.microastudio.iforms.modules.form.dto.AnswerDto;
import com.microastudio.iforms.modules.form.dto.FormDto;
import com.microastudio.iforms.modules.form.dto.FormRequestDto;
import com.microastudio.iforms.modules.form.service.FormService;
import com.microastudio.iforms.modules.system.domain.Client;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(tags = "Form")
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

    @GetMapping
    public ResultResponse getForm(@RequestParam String supperId) {
        logger.info("getForm");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (StringUtils.isEmpty(supperId)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getAllForm入参：" + supperId);
            // get form
            List<FormDto> forms = formService.getAllForms("", supperId);

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getAllForms异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @GetMapping("/questionType")
    public ResultResponse getQuestionType() {
        logger.info("getQuestionType");
        ResultResponse resultResponse = new ResultResponse();
        try {
            List<QuestionType> questionType = formService.getQuestionType();
            resultResponse.ok(questionType);
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
            resultResponse.ok(languages);
        } catch (Exception e) {
            logger.error("getLanguage异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

//    @GetMapping("/validateToken")
//    public ResultResponse validateToken(@RequestParam(value = "key") String key
//            , @RequestParam(value = "client") String client) {
//        return getClient(key, client);
//    }

    @PostMapping("/generateForm")
    public ResultResponse generateForm(@RequestBody FormDto formParam) {
        logger.info("generateForm");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (formParam == null || formParam.getSections() == null
                    || formParam.getSections().get(0).getQuestions() == null
                    || formParam.getClient() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("generateForm入参：" + JSONObject.toJSONString(formParam));

            // validateToken
            resultResponse = getClient(formParam.getClient());
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

            resultResponse.ok(form);
        } catch (
                Exception e) {
            logger.error("generateForm异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/getForms")
    public ResultResponse getForms(@RequestBody FormRequestDto dto) {
        logger.info("getForms");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (dto == null || (StringUtils.isEmpty(dto.getSupperId())
                    && (dto.getClient() == null
                    || StringUtils.isEmpty(dto.getClient().getName())
                    || StringUtils.isEmpty(dto.getClient().getToken())))) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            // validateToken
            if (dto.getClient() != null
                    && !StringUtils.isEmpty(dto.getClient().getName())
                    && !StringUtils.isEmpty(dto.getClient().getToken())) {

                resultResponse = getClient(dto.getClient());
                if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                    return resultResponse;
                }
            }

            logger.info("getAllForms入参：" + JSONObject.toJSONString(dto));
            // get form
            String clientToken = dto.getClient().getToken();
            clientToken = StringUtils.isEmpty(clientToken) ? "" : clientToken;

            List<FormDto> forms = formService.getAllForms(clientToken, dto.getSupperId());

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getAllForms异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/getAllForms")
    @PreAuthorize("hasRole('SuperAdmin')")
    public ResultResponse getAllForms() {
        logger.info("getAllForms");
        ResultResponse resultResponse = new ResultResponse();
        try {
            logger.info("getAllForms：");
            List<FormDto> forms = formService.getAllForms("", "");

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getAllForms异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/answer")
    public ResultResponse addAnswer(@RequestBody AnswerDto answerDto) {
        logger.info("answer");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (answerDto == null || answerDto.getClient() == null
                    || answerDto.getAnswers() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("answer入参：" + JSONObject.toJSONString(answerDto));

            // validateToken
            resultResponse = getClient(answerDto.getClient());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            // add answer
            String answerId = formService.addAnswer(answerDto);

            if (StringUtils.isEmpty(answerId)) {
                logger.error("answer插入异常");
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
                return resultResponse;
            }

            resultResponse.ok("");
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
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("surveyEmailTemplate.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mailService.sendHtmlMail("peng.liu@volvo.com", "主题：这是模板邮件", html);
            resultResponse.ok("");
            logger.info("成功发送邮件");
        } catch (IOException | TemplateException e) {
            logger.error("发送邮件异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_MAIL);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_MAIL);
        }

        return resultResponse;
    }

    @GetMapping("/answer")
    public ResultResponse getAnswer(@RequestParam String answerId) {
        logger.info("getAnswer");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (StringUtils.isEmpty(answerId)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getAnswer入参：" + answerId);
            List<AnswerDto> answers = formService.getAllAnswers("", answerId);

            resultResponse.ok(answers);
        } catch (Exception e) {
            logger.error("getAnswer异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @GetMapping("/answers")
    public ResultResponse getAllAnswers(@RequestBody FormRequestDto dto) {
        logger.info("getAllAnswers");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (dto == null || (StringUtils.isEmpty(dto.getSupperId())
                    && (dto.getClient() == null
                    || StringUtils.isEmpty(dto.getClient().getName())
                    || StringUtils.isEmpty(dto.getClient().getToken())))) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            // validateToken
            if (dto.getClient() != null
                    && !StringUtils.isEmpty(dto.getClient().getName())
                    && !StringUtils.isEmpty(dto.getClient().getToken())) {

                resultResponse = getClient(dto.getClient());
                if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                    return resultResponse;
                }
            }

            logger.info("getAllAnswers入参：" + JSONObject.toJSONString(dto));
            // get AllAnswers
            String clientToken = dto.getClient().getToken();
            clientToken = StringUtils.isEmpty(clientToken) ? "" : clientToken;

            List<FormDto> forms = formService.getAllForms(clientToken, dto.getSupperId());

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getAllAnswers异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    private ResultResponse getClient(Client client) {
        logger.info("getToken");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (client == null || StringUtils.isEmpty(client.getName()) || StringUtils.isEmpty(client.getToken())) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getToken入参：" + client.getName() + "," + client.getToken());

            String clientToken = formService.getClient(client.getName());
            if (!client.getToken().equals(clientToken)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_AUTH_TOKEN, CommonConstants.ERRORS_MSG_AUTH_TOKEN);
            }

            resultResponse.ok("");
        } catch (Exception e) {
            logger.error("getToken异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }
}
