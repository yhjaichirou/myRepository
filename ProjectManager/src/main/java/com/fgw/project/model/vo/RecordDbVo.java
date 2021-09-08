package com.fgw.project.model.vo;

import java.util.Date;

public class RecordDbVo {

	private Integer id;
	private Integer dbProId;
	private Integer dbTaskId;
	private Date dbTime;
	private Integer dbCount;
	private Integer dbOrg;
	private Integer dbPel;
	private Integer dbOticesPel;
	private Integer dbCreateOrg;
	private Integer dbCreater;
	private String status;
	
	private String dbTimeStr;
	private String proName;
	private String taskName;
	private String dbOrgName;
	private String dbPelName;
	private String dbOticesPelNames;
	private String dbCreateOrgName;
	private String dbCreaterName;
	
	public Integer getDbCreateOrg() {
		return dbCreateOrg;
	}
	public void setDbCreateOrg(Integer dbCreateOrg) {
		this.dbCreateOrg = dbCreateOrg;
	}
	public Integer getDbCreater() {
		return dbCreater;
	}
	public void setDbCreater(Integer dbCreater) {
		this.dbCreater = dbCreater;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDbOrgName() {
		return dbOrgName;
	}
	public void setDbOrgName(String dbOrgName) {
		this.dbOrgName = dbOrgName;
	}
	public String getDbPelName() {
		return dbPelName;
	}
	public void setDbPelName(String dbPelName) {
		this.dbPelName = dbPelName;
	}
	public String getDbOticesPelNames() {
		return dbOticesPelNames;
	}
	public void setDbOticesPelNames(String dbOticesPelNames) {
		this.dbOticesPelNames = dbOticesPelNames;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDbProId() {
		return dbProId;
	}
	public void setDbProId(Integer dbProId) {
		this.dbProId = dbProId;
	}
	public Integer getDbTaskId() {
		return dbTaskId;
	}
	public void setDbTaskId(Integer dbTaskId) {
		this.dbTaskId = dbTaskId;
	}
	public Date getDbTime() {
		return dbTime;
	}
	public void setDbTime(Date dbTime) {
		this.dbTime = dbTime;
	}
	public Integer getDbCount() {
		return dbCount;
	}
	public void setDbCount(Integer dbCount) {
		this.dbCount = dbCount;
	}
	public Integer getDbOrg() {
		return dbOrg;
	}
	public void setDbOrg(Integer dbOrg) {
		this.dbOrg = dbOrg;
	}
	public Integer getDbPel() {
		return dbPel;
	}
	public void setDbPel(Integer dbPel) {
		this.dbPel = dbPel;
	}
	public Integer getDbOticesPel() {
		return dbOticesPel;
	}
	public void setDbOticesPel(Integer dbOticesPel) {
		this.dbOticesPel = dbOticesPel;
	}
	public String getDbCreaterName() {
		return dbCreaterName;
	}
	public void setDbCreaterName(String dbCreaterName) {
		this.dbCreaterName = dbCreaterName;
	}
	public String getDbCreateOrgName() {
		return dbCreateOrgName;
	}
	public void setDbCreateOrgName(String dbCreateOrgName) {
		this.dbCreateOrgName = dbCreateOrgName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getDbTimeStr() {
		return dbTimeStr;
	}
	public void setDbTimeStr(String dbTimeStr) {
		this.dbTimeStr = dbTimeStr;
	}
	
}
