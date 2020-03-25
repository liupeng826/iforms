package com.microastudio.iforms.modules.form.controller;

import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.modules.form.domain.Email;
import com.microastudio.iforms.modules.form.service.FormService;
import com.microastudio.iforms.modules.form.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author peng
 * @date 2020/01/23
 */
@Api(tags = "Email")
@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    private final static Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private MailService mailService;
    @Autowired
    private FormService formService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostMapping("/pdfReportEmail")
    public ResultResponse sendReportEmail() {
        logger.info("开始发送邮件");
        ResultResponse resultResponse = new ResultResponse();

        boolean success = mailService.sendSimpleMail("peng.liu@volvo.com", "主题：Customer Survey Report", "Customer Survey Report");

        if (success) {
            resultResponse.ok("");
        } else {
            resultResponse.setCode(CommonConstants.ERRORS_CODE_MAIL);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_MAIL);
        }

        return resultResponse;
    }

    @PostMapping("/surveyEmail")
    public ResultResponse sendSurveyEmail() {
        logger.info("开始发送邮件");
        ResultResponse resultResponse = new ResultResponse();

        try {
            String userFeedbackUrl = "http://52.187.127.13:9191/#/view/9183eea730514d34842024ceff37929b";
            Email mail = formService.getMailByTypeAndLanguage("feedback", "en-us");

            //send email
            Map<String, Object> model = new HashMap<>();
            model.put("userFeedbackLink", userFeedbackUrl);
            model.put("body", mail.getBody());
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("surveyEmailTemplate.html");

            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            logger.info("开始发送邮件");
//                mailService.sendHtmlMail(answerDto.getCustomer().getEmail(), mail.getSubject(), html);
            mailService.sendHtmlMail("peng.liu@volvo.com", mail.getSubject(), html);
            resultResponse.ok("");
            logger.info("成功发送邮件");
        } catch (IOException | TemplateException e) {
            logger.error("发送邮件异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_MAIL);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_MAIL);
            return resultResponse;
        }

        return resultResponse;
    }
}
