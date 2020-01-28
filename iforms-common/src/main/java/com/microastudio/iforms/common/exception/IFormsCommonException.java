package com.microastudio.iforms.common.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.microastudio.iforms.common.bean.CommonConstants;

/**
 * 通用异常类
 * @author peng
 *
 */
public class IFormsCommonException extends RuntimeException {

	private static final long serialVersionUID = -8408313033879177616L;

	private String message = "";// 异常概要信息
	
	private String code = CommonConstants.ERRORS_CODE;
	
	private Map<String, ?> params;// 异常时业务入参
	
	private String guid;// 业务线唯一标识号可用uuid
	
	
	public IFormsCommonException(String message, String code) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	public IFormsCommonException(String message, String code, Object params) {
    	super(message);
        this.message = message;
        this.code = code;
        Map<String, Object> oParams = new HashMap<String, Object>();
        oParams.put("oParams", params);
        this.params = oParams;
    }
    public static void main(String[] args) {
		new IFormsCommonException("", "", "");
		new IFormsCommonException("", "", new HashMap<String, String>());
		new IFormsCommonException("", "", new ArrayList<String>());
	}
    public IFormsCommonException(String message, String code, Map<String, ?> params) {
    	super(message);
        this.message = message;
        this.code = code;
        this.params = params;
    }
    
    public IFormsCommonException(String message, String code, Object params, Throwable cause) {
    	super(cause);
        this.message = message;
        this.code = code;
        Map<String, Object> oParams = new HashMap<String, Object>();
        oParams.put("oParams", params);
        this.params = oParams;
    }
    
    public IFormsCommonException(String message, String code, Map<String, ?> params, Throwable cause) {
        super(cause);
        this.message = message;
        this.code = code;
        this.params = params;
    }
    
    public IFormsCommonException(String message, String code, Throwable cause) {
    	super(cause);
    	this.message = message;
    	this.code = code;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, ?> getParams() {
		return params;
	}

	public void setParams(Map<String, ?> params) {
		this.params = params;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
}
