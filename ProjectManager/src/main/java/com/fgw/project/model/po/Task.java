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
	private Integer orgId;
	private Integer proId;
	private String executor;
	private String executorMobile;
	private Integer stageId;
	private Date startDate;
	private Date endDate;
	private Integer priority;//优先级
	private Integer status;
	private String remark;
	private String annex;//附件 地址拼接
	
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
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
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
	
}