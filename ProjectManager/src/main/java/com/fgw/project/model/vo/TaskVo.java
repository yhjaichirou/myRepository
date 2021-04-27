package com.fgw.project.model.vo;

import java.util.Date;
import java.util.List;

public class TaskVo {

	private Integer id;
	private String title;
	private Integer pid;
	private Integer orgId;
	private Integer proId;
	private Integer executOrg;
	private Integer executor;
	private String executorMobile;
	private Integer stageId;
	private Date startDate;
	private Date endDate;
	private Integer priority;//优先级
	private Integer status;
	private String remark;
	private String annex;//附件 地址拼接
	private String preTasks;
	private Date comDate;
	private String code;
	private Date createDate;
	private String isShb;
	private Integer shb;
	private String comContent;
	private String question;
	private Date delayDate;
	
	//外键
	private String orgName;
	private String projectName;
	private String executorName;
	private String stageStr;
	private String priorityStr;
	private String startDateStr;
	private String delayDateStr;
	private String endDateStr;
	private String comDateStr;
	private String statusStr;
	private String executOrgName;
	
	private String shbName;
	private String step1;
	private String step2;
	private String step3;
	private Integer number;
	private List<Annex> fileInfos;
	private List<TaskVo> childTask;
	
	private boolean isEdit;
	private boolean isDel;

	
	//满足树形table 
	private List<TaskVo> children;
	
	// Gantt 
	private String name;
	private Date realStartDate;
	private Date realEndDate;
	private String realStartDateStr;
	private String realEndDateStr;
	
	
	public Date getRealStartDate() {
		return realStartDate;
	}
	public void setRealStartDate(Date realStartDate) {
		this.realStartDate = realStartDate;
	}
	public Date getRealEndDate() {
		return realEndDate;
	}
	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}
	public String getRealStartDateStr() {
		return realStartDateStr;
	}
	public void setRealStartDateStr(String realStartDateStr) {
		this.realStartDateStr = realStartDateStr;
	}
	public String getRealEndDateStr() {
		return realEndDateStr;
	}
	public void setRealEndDateStr(String realEndDateStr) {
		this.realEndDateStr = realEndDateStr;
	}
	public String getShbName() {
		return shbName;
	}
	public void setShbName(String shbName) {
		this.shbName = shbName;
	}
	public String getStep1() {
		return step1;
	}
	public void setStep1(String step1) {
		this.step1 = step1;
	}
	public String getStep2() {
		return step2;
	}
	public void setStep2(String step2) {
		this.step2 = step2;
	}
	public String getStep3() {
		return step3;
	}
	public void setStep3(String step3) {
		this.step3 = step3;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	public Integer getExecutor() {
		return executor;
	}
	public void setExecutor(Integer executor) {
		this.executor = executor;
	}
	public String getExecutorMobile() {
		return executorMobile;
	}
	public void setExecutorMobile(String executorMobile) {
		this.executorMobile = executorMobile;
	}
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAnnex() {
		return annex;
	}
	public void setAnnex(String annex) {
		this.annex = annex;
	}
	public String getPreTasks() {
		return preTasks;
	}
	public void setPreTasks(String preTasks) {
		this.preTasks = preTasks;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getExecutorName() {
		return executorName;
	}
	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}
	public String getStageStr() {
		return stageStr;
	}
	public void setStageStr(String stageStr) {
		this.stageStr = stageStr;
	}
	public String getPriorityStr() {
		return priorityStr;
	}
	public void setPriorityStr(String priorityStr) {
		this.priorityStr = priorityStr;
	}
	public Integer getExecutOrg() {
		return executOrg;
	}
	public void setExecutOrg(Integer executOrg) {
		this.executOrg = executOrg;
	}
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public String getExecutOrgName() {
		return executOrgName;
	}
	public void setExecutOrgName(String executOrgName) {
		this.executOrgName = executOrgName;
	}
	public Date getComDate() {
		return comDate;
	}
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}
	public String getComDateStr() {
		return comDateStr;
	}
	public void setComDateStr(String comDateStr) {
		this.comDateStr = comDateStr;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getShb() {
		return shb;
	}
	public void setShb(Integer shb) {
		this.shb = shb;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<Annex> getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(List<Annex> fileInfos) {
		this.fileInfos = fileInfos;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public List<TaskVo> getChildTask() {
		return childTask;
	}
	public void setChildTask(List<TaskVo> childTask) {
		this.childTask = childTask;
	}
	public List<TaskVo> getChildren() {
		return children;
	}
	public void setChildren(List<TaskVo> children) {
		this.children = children;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getIsShb() {
		return isShb;
	}
	public void setIsShb(String isShb) {
		this.isShb = isShb;
	}
	public boolean isEdit() {
		return isEdit;
	}
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	public boolean isDel() {
		return isDel;
	}
	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}
	public Date getDelayDate() {
		return delayDate;
	}
	public void setDelayDate(Date delayDate) {
		this.delayDate = delayDate;
	}
	public String getDelayDateStr() {
		return delayDateStr;
	}
	public void setDelayDateStr(String delayDateStr) {
		this.delayDateStr = delayDateStr;
	}
	
	
}
