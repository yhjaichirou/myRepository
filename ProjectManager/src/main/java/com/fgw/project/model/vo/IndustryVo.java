package com.fgw.project.model.vo;

import java.util.List;

public class IndustryVo {

	private Integer id;
	private String name;
	private Integer pid;
	private Integer value;
	private String label;
	private List<IndustryVo> children;
	
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
	public List<IndustryVo> getChildren() {
		return children;
	}
	public void setChildren(List<IndustryVo> children) {
		this.children = children;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
