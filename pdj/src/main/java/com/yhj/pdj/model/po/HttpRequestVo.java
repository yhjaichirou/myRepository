package com.yhj.pdj.model.po;

/**
 * 
 * @author Administrator
 *
 */
public class HttpRequestVo {

	private String state;
	private Integer code;
	private String error;

	private String errormsg;


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	
	
}
