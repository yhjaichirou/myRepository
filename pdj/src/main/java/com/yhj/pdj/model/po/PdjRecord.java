package com.yhj.pdj.model.po;

import java.util.Date;

/**
 * 排队机上报信息
 * @author Administrator
 *
 */
public class PdjRecord {

	private Integer id;
	private Integer type;
	private Integer status;
	private Date createTime;
	private String recordCode;
	
	private String areaId;//行政代码
	private String areaName;//行政区名称
	private String taskId;//事项编码
	private String taskName;//事项名称
	private String proDepartId;//受理部门编码
	private String proDepart;//受理部门
	private String userName;//班里人姓名
	private String userCert;//班里人身份证号
	private String phonumber;//电话号码
	private String appId;//系统分配的appid
	private String certType;//证件类型  身份证传：111
	private String deptCode;//受理部门信用代码  
	private String proStatus;//1 受理  ， 2 已受理  3已办结
	private String taskType;//及办件 =1 ，承诺办件=2
	private String subMatter;//申请人类型（1 自然人  2 企业法人 3事业法人  4 社会组织法人  5非法人企业  6 行政机关    9其他组织）
	private String proManager;//经办人姓名
	
	//view
	private String account;
	private String createTimeStr;
	
	private String pf;
	private String apprate;//评价级别
	private String mouldAp;//评价选项
	private String apprateDetail;//评价详情
	
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getRecordCode() {
		return recordCode;
	}
	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
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
	public String getProDepartId() {
		return proDepartId;
	}
	public void setProDepartId(String proDepartId) {
		this.proDepartId = proDepartId;
	}
	public String getProDepart() {
		return proDepart;
	}
	public void setProDepart(String proDepart) {
		this.proDepart = proDepart;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCert() {
		return userCert;
	}
	public void setUserCert(String userCert) {
		this.userCert = userCert;
	}
	public String getPhonumber() {
		return phonumber;
	}
	public void setPhonumber(String phonumber) {
		this.phonumber = phonumber;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getProStatus() {
		return proStatus;
	}
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getSubMatter() {
		return subMatter;
	}
	public void setSubMatter(String subMatter) {
		this.subMatter = subMatter;
	}
	public String getProManager() {
		return proManager;
	}
	public void setProManager(String proManager) {
		this.proManager = proManager;
	}
	public String getPf() {
		return pf;
	}
	public void setPf(String pf) {
		this.pf = pf;
	}
	public String getApprate() {
		return apprate;
	}
	public void setApprate(String apprate) {
		this.apprate = apprate;
	}
	public String getApprateDetail() {
		return apprateDetail;
	}
	public void setApprateDetail(String apprateDetail) {
		this.apprateDetail = apprateDetail;
	}
	public String getMouldAp() {
		return mouldAp;
	}
	public void setMouldAp(String mouldAp) {
		this.mouldAp = mouldAp;
	}
	
	
	
}
