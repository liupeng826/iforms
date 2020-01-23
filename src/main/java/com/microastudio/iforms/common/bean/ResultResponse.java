package com.microastudio.iforms.common.bean;

/**
 * @author peng
 * 统一接口返回类
 */
public class ResultResponse<T> {

    private String code;
    private String message;
    private T data;

    public ResultResponse() {
    }

    public ResultResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 通用成功结果对象
     *
     * @return
     */
    public static <T> ResultResponse<T> success(T value) {
        ResultResponse<T> res = new ResultResponse<T>();
        res.setCode(CommonConstants.SUCCESS_CODE);
        res.setMessage(CommonConstants.SUCCESS_MSG);
        res.setData(value == null ? (T) "" : value);
        return res;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
