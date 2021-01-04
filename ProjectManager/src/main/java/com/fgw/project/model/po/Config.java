package com.fgw.project.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "config")
public class Config implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer yjDay;
	private Date yjTime;
	private String yjType;
	private Integer mesMessage;
	private String mesDefaultPel;
	private Integer dwMaxPel;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "yj_day")
	public Integer getYjDay() {
		return yjDay;
	}

	public void setYjDay(Integer yjDay) {
		this.yjDay = yjDay;
	}

	@Column(name = "yj_time")
	public Date getYjTime() {
		return yjTime;
	}

	public void setYjTime(Date yjTime) {
		this.yjTime = yjTime;
	}

	@Column(name = "yj_type")
	public String getYjType() {
		return yjType;
	}

	public void setYjType(String yjType) {
		this.yjType = yjType;
	}

	@Column(name = "mes_message")
	public Integer getMesMessage() {
		return mesMessage;
	}

	public void setMesMessage(Integer mesMessage) {
		this.mesMessage = mesMessage;
	}

	@Column(name = "mes_default_pel")
	public String getMesDefaultPel() {
		return mesDefaultPel;
	}

	public void setMesDefaultPel(String mesDefaultPel) {
		this.mesDefaultPel = mesDefaultPel;
	}

	@Column(name = "dw_max_pel")
	public Integer getDwMaxPel() {
		return dwMaxPel;
	}

	public void setDwMaxPel(Integer dwMaxPel) {
		this.dwMaxPel = dwMaxPel;
	}
	

	
}
