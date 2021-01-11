package com.fgw.project.model.vo;

import java.util.List;

/**
 * 阿里云 短信返回信息对象
 * @author yhj
 * @date 2021年1月11日
 */
public class SendMessageResult {
	
	private String Code ;
	private String Message;
	private String RequestId;
	private List<SmsSendDetailDTO> SmsSendDetailDTOs;
	private String TotalCount;
	
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getRequestId() {
		return RequestId;
	}
	public void setRequestId(String requestId) {
		RequestId = requestId;
	}
	public List<SmsSendDetailDTO> getSmsSendDetailDTOs() {
		return SmsSendDetailDTOs;
	}
	public void setSmsSendDetailDTOs(List<SmsSendDetailDTO> smsSendDetailDTOs) {
		SmsSendDetailDTOs = smsSendDetailDTOs;
	}
	public String getTotalCount() {
		return TotalCount;
	}
	public void setTotalCount(String totalCount) {
		TotalCount = totalCount;
	}
	
}
