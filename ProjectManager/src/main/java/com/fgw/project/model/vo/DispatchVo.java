package com.fgw.project.model.vo;

import java.util.Date;

/**
 * 调度表vo
 * @author yhj
 * @date 2021年8月31日
 */
public class DispatchVo {

	private Integer id;
	private String proId;
	private Date dTime;
	private Date dStartTime;
	private Date dEndTime;
	private Integer operUserId;
	private Integer dOrgId;
	private Integer dUserId;
	private String dContent;//
	private Integer status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public Date getdTime() {
		return dTime;
	}
	public void setdTime(Date dTime) {
		this.dTime = dTime;
	}
	public Date getdStartTime() {
		return dStartTime;
	}
	public void setdStartTime(Date dStartTime) {
		this.dStartTime = dStartTime;
	}
	public Date getdEndTime() {
		return dEndTime;
	}
	public void setdEndTime(Date dEndTime) {
		this.dEndTime = dEndTime;
	}
	public Integer getOperUserId() {
		return operUserId;
	}
	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}
	public Integer getdOrgId() {
		return dOrgId;
	}
	public void setdOrgId(Integer dOrgId) {
		this.dOrgId = dOrgId;
	}
	public Integer getdUserId() {
		return dUserId;
	}
	public void setdUserId(Integer dUserId) {
		this.dUserId = dUserId;
	}
	public String getdContent() {
		return dContent;
	}
	public void setdContent(String dContent) {
		this.dContent = dContent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}
