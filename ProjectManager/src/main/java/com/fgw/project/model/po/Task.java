package com.fgw.project.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
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
	private Integer shb;
	private Integer dbCount;
	private Date dbDate;
	
	private String comContent;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "pro_id")
	public Integer getProId() {
		return proId;
	}
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	@Column(name = "executor")
	public Integer getExecutor() {
		return executor;
	}
	public void setExecutor(Integer executor) {
		this.executor = executor;
	}
	
	@Column(name = "executor_mobile")
	public String getExecutorMobile() {
		return executorMobile;
	}
	public void setExecutorMobile(String executorMobile) {
		this.executorMobile = executorMobile;
	}
	
	@Column(name = "stage_id")
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name = "priority")
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "annex")
	public String getAnnex() {
		return annex;
	}
	public void setAnnex(String annex) {
		this.annex = annex;
	}
	
	@Column(name = "pre_tasks")
	public String getPreTasks() {
		return preTasks;
	}
	public void setPreTasks(String preTasks) {
		this.preTasks = preTasks;
	}
	
	@Column(name = "execut_org")
	public Integer getExecutOrg() {
		return executOrg;
	}
	public void setExecutOrg(Integer executOrg) {
		this.executOrg = executOrg;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "shb")
	public Integer getShb() {
		return shb;
	}
	public void setShb(Integer shb) {
		this.shb = shb;
	}
	
	@Column(name = "com_date")
	public Date getComDate() {
		return comDate;
	}
	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

	@Column(name = "com_content")
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	
	@Column(name = "db_count")
	public Integer getDbCount() {
		return dbCount;
	}
	public void setDbCount(Integer dbCount) {
		this.dbCount = dbCount;
	}
	
	@Column(name = "db_date")
	public Date getDbDate() {
		return dbDate;
	}
	public void setDbDate(Date dbDate) {
		this.dbDate = dbDate;
	}
	
	@Column(name = "pid")
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
}