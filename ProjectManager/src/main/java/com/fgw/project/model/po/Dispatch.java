package com.fgw.project.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dispatch")
public class Dispatch implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer proId;
	private Date dTime;
	private Date dStartTime;
	private Date dEndTime;
	private Integer operUserId;
	private Integer dOrgId;
	private Integer dUserId;
	private String dContent;
	private Integer status;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "pro_id")
	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	@Column(name = "d_time")
	public Date getdTime() {
		return dTime;
	}

	public void setdTime(Date dTime) {
		this.dTime = dTime;
	}

	@Column(name = "d_start_time")
	public Date getdStartTime() {
		return dStartTime;
	}

	public void setdStartTime(Date dStartTime) {
		this.dStartTime = dStartTime;
	}

	@Column(name = "d_end_time")
	public Date getdEndTime() {
		return dEndTime;
	}

	public void setdEndTime(Date dEndTime) {
		this.dEndTime = dEndTime;
	}

	@Column(name = "oper_user_id")
	public Integer getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}

	@Column(name = "d_org_id")
	public Integer getdOrgId() {
		return dOrgId;
	}

	public void setdOrgId(Integer dOrgId) {
		this.dOrgId = dOrgId;
	}

	@Column(name = "d_user_id")
	public Integer getdUserId() {
		return dUserId;
	}

	public void setdUserId(Integer dUserId) {
		this.dUserId = dUserId;
	}

	@Column(name = "d_content")
	public String getdContent() {
		return dContent;
	}

	public void setdContent(String dContent) {
		this.dContent = dContent;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
}
