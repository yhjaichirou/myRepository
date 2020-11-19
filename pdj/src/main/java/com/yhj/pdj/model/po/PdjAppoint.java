package com.yhj.pdj.model.po;

import java.util.Date;

/**
 * 排队机预约信息
 * @author Administrator
 *
 */
public class PdjAppoint {

	private Integer id;
	private String openid;
	private String appointCode;
	private String userName;//班里人姓名
	private String idcard;//班里人身份证号
	private String phone;//电话号码
	private String taskId;//事项编码
	private String taskName;//事项名称
	private Date createTime;//预约时间
	private Integer status;
	private String appId;//系统分配的appid
	private String areaId;//行政代码
	private String areaName;//行政区名称
	private String codePath;
	private String orderNumber;
	private String orderDate;
	//view
	private String createTimeStr;
	
	public String getCodePath() {
		return codePath;
	}
	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppointCode() {
		return appointCode;
	}
	public void setAppointCode(String appointCode) {
		this.appointCode = appointCode;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
