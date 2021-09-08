package com.fgw.project.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "record_db")
public class RecordDb implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
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
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "db_pro_id")
	public Integer getDbProId() {
		return dbProId;
	}

	public void setDbProId(Integer dbProId) {
		this.dbProId = dbProId;
	}

	@Column(name = "db_task_id")
	public Integer getDbTaskId() {
		return dbTaskId;
	}

	public void setDbTaskId(Integer dbTaskId) {
		this.dbTaskId = dbTaskId;
	}

	@Column(name = "db_time")
	public Date getDbTime() {
		return dbTime;
	}

	public void setDbTime(Date dbTime) {
		this.dbTime = dbTime;
	}

	@Column(name = "db_count")
	public Integer getDbCount() {
		return dbCount;
	}

	public void setDbCount(Integer dbCount) {
		this.dbCount = dbCount;
	}

	@Column(name = "db_org")
	public Integer getDbOrg() {
		return dbOrg;
	}

	public void setDbOrg(Integer dbOrg) {
		this.dbOrg = dbOrg;
	}

	@Column(name = "db_pel")
	public Integer getDbPel() {
		return dbPel;
	}

	public void setDbPel(Integer dbPel) {
		this.dbPel = dbPel;
	}

	@Column(name = "db_otices_pel")
	public Integer getDbOticesPel() {
		return dbOticesPel;
	}

	public void setDbOticesPel(Integer dbOticesPel) {
		this.dbOticesPel = dbOticesPel;
	}
	
	@Column(name = "db_create_org")
	public Integer getDbCreateOrg() {
		return dbCreateOrg;
	}

	public void setDbCreateOrg(Integer dbCreateOrg) {
		this.dbCreateOrg = dbCreateOrg;
	}

	@Column(name = "db_creater")
	public Integer getDbCreater() {
		return dbCreater;
	}

	public void setDbCreater(Integer dbCreater) {
		this.dbCreater = dbCreater;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}