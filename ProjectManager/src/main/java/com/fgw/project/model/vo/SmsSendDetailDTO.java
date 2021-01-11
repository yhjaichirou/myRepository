package com.fgw.project.model.vo;

/**
 * 短信发送明细
 * @author yhj
 * @date 2021年1月11日
 */
public class SmsSendDetailDTO {

	private String Content; //短信内容
	private String ErrCode; //运营商短信状态码
	private String OutId; //外部流水扩展字段
	private String PhoneNum; //接收短信的手机号码
	private String ReceiveDate; // 短信接收日期和时间
	private String SendDate;//短信发送日期和时间
	private Long SendStatus;//短信发送状态，包括： 1：等待回执。 2：发送失败。 3：发送成功。
	private String TemplateCode; //短信模板ID
	
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getErrCode() {
		return ErrCode;
	}
	public void setErrCode(String errCode) {
		ErrCode = errCode;
	}
	public String getOutId() {
		return OutId;
	}
	public void setOutId(String outId) {
		OutId = outId;
	}
	public String getPhoneNum() {
		return PhoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}
	public String getReceiveDate() {
		return ReceiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		ReceiveDate = receiveDate;
	}
	public String getSendDate() {
		return SendDate;
	}
	public void setSendDate(String sendDate) {
		SendDate = sendDate;
	}
	public Long getSendStatus() {
		return SendStatus;
	}
	public void setSendStatus(Long sendStatus) {
		SendStatus = sendStatus;
	}
	public String getTemplateCode() {
		return TemplateCode;
	}
	public void setTemplateCode(String templateCode) {
		TemplateCode = templateCode;
	}
	
	
}
