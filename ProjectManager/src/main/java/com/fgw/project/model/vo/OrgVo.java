package com.fgw.project.model.vo;

import java.util.Date;

public class OrgVo {

	private Integer id;
	private String name;
	private String position;
	private String manager;
	private String managerMobile;
	private Integer status;
	private Integer capacityValue;
	private Integer level;
	private Integer pid;
	private Integer property;
	private Integer type;
	
	//外键
	private String managerName;
	private String propertyStr;
	private String typeStr;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getManagerMobile() {
		return managerMobile;
	}
	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCapacityValue() {
		return capacityValue;
	}
	public void setCapacityValue(Integer capacityValue) {
		this.capacityValue = capacityValue;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getProperty() {
		return property;
	}
	public void setProperty(Integer property) {
		this.property = property;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getPropertyStr() {
		return propertyStr;
	}
	public void setPropertyStr(String propertyStr) {
		this.propertyStr = propertyStr;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
}
