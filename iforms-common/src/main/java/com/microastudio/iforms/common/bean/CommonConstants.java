package com.microastudio.iforms.common.bean;

/**
 * 公共常量类
 *
 * @author peng
 */
public class CommonConstants {

    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MSG = "ok";

    public static final String ERRORS_CODE = "-999";

    public static final String ERRORS_CODE_EMPTY = "-1";
    public static final String ERRORS_MSG_EMPTY = "参数为空";

    public static final String ERRORS_CODE_SYSTEM = "-9";
    public static final String ERRORS_MSG_SYSTEM = "系统异常";

    public static final String ERRORS_CODE_AUTH_SECRET_KEY_NOT_FOUND = "-8";
    public static final String ERRORS_MSG_AUTH_SECRET_KEY_NOT_FOUND = "授权密钥未设置";

    public static final String ERRORS_CODE_AUTH_TOKEN = "-7";
    public static final String ERRORS_MSG_AUTH_TOKEN = "token失效或不存在";

    public static final String ERRORS_CODE_MAIL = "-6";
    public static final String ERRORS_MSG_MAIL = "邮件发送异常";

    public static final String ERRORS_CODE_EXISTS = "-5";
    public static final String ERRORS_MSG_EXISTS = "数据已存在";

    // token用
    public static final String AUTHORIZATION = "authStr";
    public static final String CURRENT_USER_ID = "userId";
    public static final String USER_SESSION_KEY_PREFIX = "USER_TOKEN_KEY_";
    public static final long TOKEN_EXPIRES_DAYS = 5;


    public static final String QUESTION_TYPE_KEY = "QUESTION_TYPE_KEY";
    public static final String LANGUAGE_KEY = "LANGUAGE_KEY";

}
