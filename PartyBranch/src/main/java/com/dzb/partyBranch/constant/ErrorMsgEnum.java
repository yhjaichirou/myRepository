package com.dzb.partyBranch.constant;

public enum ErrorMsgEnum {
	
	NO_PRIMARY(300,"暂无权限"),
	SUCCESS_PRIMARY(200,"允许");
	
	private String msg ;
	private Integer status;
	ErrorMsgEnum(Integer status ,String msg){
		this.msg = msg;
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
