package com.microastudio.iforms.modules.form.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.microastudio.iforms.common.bean.CommonConstants;
import com.microastudio.iforms.common.bean.ResultResponse;
import com.microastudio.iforms.common.utils.RoleEnum;
import com.microastudio.iforms.common.utils.SecurityUtils;
import com.microastudio.iforms.modules.form.domain.Answer;
import com.microastudio.iforms.modules.form.domain.Form;
import com.microastudio.iforms.modules.form.domain.Language;
import com.microastudio.iforms.modules.form.domain.QuestionType;
import com.microastudio.iforms.modules.form.dto.*;
import com.microastudio.iforms.modules.form.service.FormService;
import com.microastudio.iforms.modules.form.service.MailService;
import com.microastudio.iforms.modules.system.domain.Client;
import com.microastudio.iforms.modules.system.domain.User;
import com.microastudio.iforms.modules.system.dto.UserDto;
import com.microastudio.iforms.modules.system.service.DeptService;
import com.microastudio.iforms.modules.system.service.UserService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author peng
 * @date 2020/01/23
 */
@Api(tags = "Form")
@RestController
@RequestMapping("/api/v1/form")
public class FormController {
    private final static Logger logger = LoggerFactory.getLogger(FormController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FormService formService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${fromMail}")
    private String from;
    //-----------------------问卷--------------------------------------

    @ApiOperation("免授权：获取单个问卷")
    @GetMapping
    public ResultResponse getForm(@RequestParam String superFormId) {
        logger.info("getForm");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (StringUtils.isEmpty(superFormId)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getAllForm入参：" + superFormId);
            // get form
            List<FormDto> forms = formService.getForms("", superFormId);

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getAllForms异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/getFormsByUser")
    public ResultResponse getFormsByUser(@RequestBody FormRequestDto dto) {
        logger.info("getFormsByUser");

        UserDto user = userService.findByName(SecurityUtils.getUsername());

        ResultResponse resultResponse = new ResultResponse();

        try {
            if (dto == null || user == null
                    || dto.getClient() == null
                    || StringUtils.isEmpty(dto.getClient().getName())
                    || StringUtils.isEmpty(dto.getClient().getToken())
            ) {
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

            logger.info("getFormsByUser 入参：" + JSONObject.toJSONString(dto));
            // get form
            String clientToken = dto.getClient().getToken();
            clientToken = StringUtils.isEmpty(clientToken) ? "" : clientToken;

            String userId = user.getId().toString();
            List<FormDto> forms = formService.getFormsByUserId(clientToken, userId);

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getFormsByUser 异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @ApiOperation("获取问卷")
    @PostMapping("/getFormsByLevel")
    public ResultResponse getFormsByLevel(@RequestBody FormRequestDto dto) {
        logger.info("getFormsByLevel");

        ResultResponse resultResponse = new ResultResponse();
        UserDto user = userService.findByName(SecurityUtils.getUsername());

        try {
            if (dto == null
                    || dto.getClient() == null
                    || StringUtils.isEmpty(dto.getClient().getName())
                    || StringUtils.isEmpty(dto.getClient().getToken())
                    || user == null
                    || user.getDept() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            // validateToken
            resultResponse = getClient(dto.getClient());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            logger.info("getFormsByLevel 入参：" + JSONObject.toJSONString(dto));
            // get form
            String clientToken = dto.getClient().getToken();

            String deptId;
            if (!StringUtils.isEmpty(dto.getDeptId())) {
                deptId = dto.getDeptId();
            } else {
                deptId = user.getDept().getId();
            }

            String marketId;
            if (!StringUtils.isEmpty(dto.getMarketId())) {
                marketId = dto.getMarketId();
            } else {
                marketId = user.getDept().getMarket().getId();
            }

            List<FormDto> forms = formService.getFormsByDeptAndMarket(clientToken, deptId, marketId);

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getFormsByLevel 异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @PostMapping("/getFormsByClient")
    public ResultResponse getFormsByClient(@RequestBody FormRequestDto dto) {
        logger.info("getFormsByClient");
        ResultResponse resultResponse = new ResultResponse();

        try {
            if (dto == null
                    || dto.getClient() == null
                    || StringUtils.isEmpty(dto.getClient().getName())
                    || StringUtils.isEmpty(dto.getClient().getToken())) {
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

            logger.info("getFormsByClient 入参：" + JSONObject.toJSONString(dto));
            // get form
            String clientToken = dto.getClient().getToken();
            clientToken = StringUtils.isEmpty(clientToken) ? "" : clientToken;

            List<FormDto> forms = formService.getForms(clientToken, dto.getSuperFormId());

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getFormsByClient 异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @ApiOperation("超级管理员获取所有问卷")
    @PostMapping("/getAllForms")
    @PreAuthorize("hasRole('SuperAdmin')")
    public ResultResponse getAllForms() {
        logger.info("getAllForms");
        ResultResponse resultResponse = new ResultResponse();
        try {
            logger.info("getAllForms：");
            List<FormDto> forms = formService.getForms("", "");

            resultResponse.ok(forms);
        } catch (Exception e) {
            logger.error("getAllForms异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @ApiOperation("创建问卷")
    @PostMapping
    public ResultResponse addForm(@RequestBody FormDto formParam) {
        logger.info("addForm");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (formParam == null || formParam.getSections() == null
                    || formParam.getSections().get(0).getQuestions() == null
                    || formParam.getClient() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("addForm入参：" + JSONObject.toJSONString(formParam));

            // validateToken
            resultResponse = getClient(formParam.getClient());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            formParam.getClient().setId(Long.valueOf((String) resultResponse.getData()));
            // generate form
            Form form = formService.addForm(formParam);

            if (form == null) {
                logger.error("addForm异常：Form is null");
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
                return resultResponse;
            }

            resultResponse.ok(form);
        } catch (
                Exception e) {
            logger.error("addForm异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @ApiOperation("变更问卷")
    @PutMapping
    public ResultResponse updateForm(@RequestBody FormDto dto) {
        logger.info("updateForm");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (dto == null || dto.getId() == null
                    || StringUtils.isEmpty(dto.getSuperFormId())
                    || dto.getSections() == null
                    || dto.getSections().get(0).getQuestions() == null
                    || dto.getClient() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("updateForm 入参：" + JSONObject.toJSONString(dto));

            // validateToken
            resultResponse = getClient(dto.getClient());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            dto.getClient().setId(Long.valueOf((String) resultResponse.getData()));
            // generate form
            Form form = formService.updateForm(dto);

            if (form == null) {
                logger.error("updateForm 异常：Form is null");
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
                return resultResponse;
            }

            resultResponse.ok("");
        } catch (
                Exception e) {
            logger.error("updateForm 异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @ApiOperation("发布问卷")
    @PutMapping("/formStatus")
    public ResultResponse updateFormStatus(@RequestBody FormDto dto) {
        logger.info("updateFormStatus");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (dto == null || dto.getId() == null
                    || StringUtils.isEmpty(dto.getSuperFormId())
                    || dto.getClient() == null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }
            logger.info("updateFormStatus 入参：" + JSONObject.toJSONString(dto));

            // validateToken
            resultResponse = getClient(dto.getClient());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            // generate form
            Form form = formService.updateFormStatus(dto);

            if (form == null) {
                logger.error("updateFormStatus 异常：Form is null");
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
                return resultResponse;
            }

            resultResponse.ok("");
        } catch (
                Exception e) {
            logger.error("updateFormStatus 异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    //-----------------------反馈--------------------------------------

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
            return resultResponse;
        }

        if (answerDto != null && answerDto.getNeedSendEmail() && answerDto.getCustomer() != null
                && !StringUtils.isEmpty(answerDto.getCustomer().getEmail())) {
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
                return resultResponse;
            }
        }

        return resultResponse;
    }

    @ApiOperation("免授权：获取单个反馈")
    @GetMapping("/answer")
    public ResultResponse getAnswer(@RequestParam String answerId) {
        logger.info("getAnswer");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (StringUtils.isEmpty(answerId)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getAnswer入参：" + answerId);
            List<Answer> answers = formService.getAnswersByAnswerId("", answerId);

            resultResponse.ok(answers);
        } catch (Exception e) {
            logger.error("getAnswer异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }

    @ApiOperation("免授权：获取单个反馈和其问卷")
    @GetMapping("/answersWithForm")
    public ResultResponse getAnswersWithForm(@RequestParam String answerId) {
        logger.info("getAnswersWithForm 入参：" + answerId);

        ResultResponse resultResponse = new ResultResponse();
        resultResponse.ok("");

        try {
            if (StringUtils.isEmpty(answerId)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            FormDto form = formService.getAnswersWithFormByAnswerId("", answerId);
            resultResponse.ok(form);
        } catch (Exception e) {
            logger.error("getAnswersWithForm 异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }

        return resultResponse;
    }

    @ApiOperation("统计：获取反馈和其问卷(month不为空则优先查询)")
    @PostMapping("/answersForMarket")
    public ResultResponse getAnswersForMarket(@RequestBody AnswerRequestDto dto) {
        logger.info("get answers For Market");
        ResultResponse resultResponse = new ResultResponse();

        UserDto user = userService.findByName(SecurityUtils.getUsername());
        String userMarketId = user.getDept().getMarket().getId();

        // FormId为空，取所有数据
        if (dto == null
                || dto.getClient() == null
                || StringUtils.isEmpty(dto.getClient().getName())
                || StringUtils.isEmpty(dto.getClient().getToken())) {
            return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
        }

        // validateToken
        resultResponse = getClient(dto.getClient());
        if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
            return resultResponse;
        }

        try {
            if (user == null || user.getRole() > RoleEnum.ADMIN.getValue()
                    || (user.getRole() == RoleEnum.ADMIN.getValue() && !userMarketId.equals(dto.getMarketId()))) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_SYSTEM, CommonConstants.ERRORS_CODE_SYSTEM);
            }

            String clientToken = dto.getClient().getToken();
            clientToken = StringUtils.isEmpty(clientToken) ? "" : clientToken;

            String yearMonth = "";
            String from = "";
            String to = "";
            Date date = DateUtil.date();

            if (!StringUtils.isEmpty(dto.getMonth())) {
                yearMonth = dto.getMonth();
            } else {
                if (StringUtils.isEmpty(to)) {
                    to = DateUtil.format(date, "yyyy-MM-dd");
                } else {
                    to = DateUtil.format(dto.getTo(), "yyyy-MM-dd");
                }

                if (StringUtils.isEmpty(from)) {
                    from = DateUtil.format(DateUtil.offsetMonth(date, -12), "yyyy-MM-dd");
                } else {
                    from = DateUtil.format(dto.getFrom(), "yyyy-MM-dd");
                }
            }

            List<FormDto> answers = formService.getAnswers(clientToken, dto.getFormId(), userMarketId, dto.getDealerId(), yearMonth, from, to);
            resultResponse.ok(answers);

        } catch (Exception e) {
            logger.error("getAllAnswers异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }

        return resultResponse;
    }

    //-----------------------统计--------------------------------------

    @ApiOperation("统计：按选项统计反馈数量")
    @PostMapping("/AnswerOptionsStatistics")
    public ResultResponse getQuestionnaireOptionStatistics(@RequestBody AnswerRequestDto dto) {
        logger.info("get Questionnaire Option Statistics");
        ResultResponse resultResponse = new ResultResponse();

        UserDto user = userService.findByName(SecurityUtils.getUsername());
        String marketId = user.getDept().getMarket().getId();

        // FormId为空，取所有数据
        if (dto == null
                || dto.getClient() == null
                || StringUtils.isEmpty(dto.getClient().getName())
                || StringUtils.isEmpty(dto.getClient().getToken())) {
            return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
        }

        // validateToken
        resultResponse = getClient(dto.getClient());
        if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
            return resultResponse;
        }

        try {
            if (user == null || user.getRole() > RoleEnum.ADMIN.getValue()
                    || (user.getRole() == RoleEnum.ADMIN.getValue() && !marketId.equals(dto.getMarketId()))) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_SYSTEM, CommonConstants.ERRORS_CODE_SYSTEM);
            }

            if (!StringUtils.isEmpty(dto.getMarketId())) {
                marketId = dto.getMarketId();
            }
//            String clientToken = dto.getClient().getToken();
//            clientToken = StringUtils.isEmpty(clientToken) ? "" : clientToken;

            String yearMonth = "";
            String from = "";
            String to = "";
            Date date = DateUtil.date();

            if (!StringUtils.isEmpty(dto.getMonth())) {
                yearMonth = dto.getMonth();
            } else {
                if (StringUtils.isEmpty(to)) {
                    to = DateUtil.format(date, "yyyy-MM-dd");
                } else {
                    to = DateUtil.format(dto.getTo(), "yyyy-MM-dd");
                }
                if (StringUtils.isEmpty(from)) {
                    from = DateUtil.format(DateUtil.offsetMonth(date, -12), "yyyy-MM-dd");
                } else {
                    from = DateUtil.format(dto.getFrom(), "yyyy-MM-dd");
                }
            }

            List<QuestionnaireStatisticsDto> answers = formService.getQuestionnaireOptionStatistics(dto.getFormId(), marketId, dto.getDealerId(), yearMonth, from, to);
            resultResponse.ok(answers);

        } catch (Exception e) {
            logger.error("get Questionnaire Option Statistics异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }

        return resultResponse;
    }

    @ApiOperation("统计：过去12个月反馈数量")
    @PostMapping("/AnswerStatistics")
    public ResultResponse getQuestionnaireStatistics(@RequestBody AnswerRequestDto dto) {
        logger.info("get Questionnaire Statistics");
        ResultResponse resultResponse = new ResultResponse();

        UserDto user = userService.findByName(SecurityUtils.getUsername());
        String marketId = user.getDept().getMarket().getId();

        // FormId为空，取所有数据
        if (dto == null
                || dto.getClient() == null
                || StringUtils.isEmpty(dto.getClient().getName())
                || StringUtils.isEmpty(dto.getClient().getToken())) {
            return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
        }

        // validateToken
        resultResponse = getClient(dto.getClient());
        if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
            return resultResponse;
        }

        try {
            if (user == null || user.getRole() > RoleEnum.ADMIN.getValue()
                    || (user.getRole() == RoleEnum.ADMIN.getValue() && !marketId.equals(dto.getMarketId()))) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_SYSTEM, CommonConstants.ERRORS_CODE_SYSTEM);
            }

            if (!StringUtils.isEmpty(dto.getMarketId())) {
                marketId = dto.getMarketId();
            }
//            String clientToken = dto.getClient().getToken();
//            clientToken = StringUtils.isEmpty(clientToken) ? "" : clientToken;

            String from = "";
            String to = "";
            Date date = DateUtil.date();

            if (StringUtils.isEmpty(to)) {
                to = DateUtil.format(date, "yyyy-MM-dd");
            } else {
                to = DateUtil.format(dto.getTo(), "yyyy-MM-dd");
            }
            if (StringUtils.isEmpty(from)) {
                from = DateUtil.format(DateUtil.offsetMonth(date, -12), "yyyy-MM-dd");
            } else {
                from = DateUtil.format(dto.getFrom(), "yyyy-MM-dd");
            }

            List<QuestionnaireStatisticsDto> answers = formService.getQuestionnaireStatistics(dto.getFormId(), marketId, dto.getDealerId(), from, to);
            resultResponse.ok(answers);

        } catch (Exception e) {
            logger.error("get Questionnaire Statistics异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }

        return resultResponse;
    }

    //-----------------------其他--------------------------------------

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

    @ApiOperation("免授权：用户和部门注册")
    @PostMapping("/registerDealer")
    public ResultResponse registerDealer(@RequestBody UserDto userDto) {
        logger.info("registerDealer");

        ResultResponse resultResponse = new ResultResponse();
        try {
            if (userDto == null || userDto.getDept() == null || StringUtils.isEmpty(userDto.getUserName())) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            resultResponse = getClient(userDto.getClient());
            if (!CommonConstants.SUCCESS_CODE.equals(resultResponse.getCode())) {
                return resultResponse;
            }

            logger.info("registerDealer：DEPT");
            Timestamp time = new Timestamp(System.currentTimeMillis());
            userDto.getClient().setId(Long.valueOf((String) resultResponse.getData()));
            String marketId = userDto.getDept().getMarket().getId();
            userDto.getDept().getMarket().setId(StringUtils.isEmpty(marketId) ? "1" : marketId);
            userDto.getDept().setMarketId(StringUtils.isEmpty(marketId) ? "1" : marketId);
            userDto.getDept().setCreatedBy(userDto.getUserName());
            userDto.getDept().setCreatedDate(time);
            userDto.getDept().setModifiedBy(userDto.getUserName());
            userDto.getDept().setModifiedDate(time);

            UserDto newUserDto = userService.findByUserName(userDto.getUserName());
            if (newUserDto != null) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EXISTS, CommonConstants.ERRORS_MSG_EXISTS);
            }

            userDto.getDept().setIsActive(Byte.valueOf("0"));

            logger.info("registerDealer：USER");
            userDto.setIsActive(Byte.valueOf("0"));
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setRole(RoleEnum.USER.getValue());
            userDto.setCreatedBy(userDto.getUserName());
            userDto.setCreatedDate(time);
            userDto.setModifiedBy(userDto.getUserName());
            userDto.setModifiedDate(time);
            User user = userService.createUserAndDept(userDto);

            if (user != null) {
                //send email
                logger.info("开始发送邮件");
                mailService.sendSimpleMail(from, "GTA CS Dealer register",
                        " Dealer:" + user.getUserName()
                                + "\n Created Date:" + user.getCreatedDate()
                                + "\n Created By:" + user.getCreatedBy());

                resultResponse.ok("");
            } else {
                resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
                resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
            }
        } catch (Exception e) {
            logger.error("registerDealer 异常：" + e.getMessage(), e);
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

    private ResultResponse getClient(Client client) {
        logger.info("getToken");
        ResultResponse resultResponse = new ResultResponse();
        try {
            if (client == null || StringUtils.isEmpty(client.getName()) || StringUtils.isEmpty(client.getToken())) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_EMPTY, CommonConstants.ERRORS_MSG_EMPTY);
            }

            logger.info("getToken入参：" + client.getName() + "," + client.getToken());

            String id = formService.getClient(client.getName(), client.getToken());
            if (StringUtils.isEmpty(id)) {
                return new ResultResponse(CommonConstants.ERRORS_CODE_AUTH_TOKEN, CommonConstants.ERRORS_MSG_AUTH_TOKEN);
            }

            resultResponse.ok(id);
        } catch (Exception e) {
            logger.error("getToken异常：" + e.getMessage(), e);
            resultResponse.setCode(CommonConstants.ERRORS_CODE_SYSTEM);
            resultResponse.setMessage(CommonConstants.ERRORS_MSG_SYSTEM);
        }
        return resultResponse;
    }
}
