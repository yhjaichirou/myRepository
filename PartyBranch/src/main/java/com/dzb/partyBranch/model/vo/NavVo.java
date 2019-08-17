package com.dzb.partyBranch.model.vo;

import java.util.List;

import javax.persistence.Column;

/**
 * 导航nav
 * @author yhj
 * @date 2018/11/8
 */
public class NavVo {
	private Integer id;
	private String name;
	private Integer pid;
	private String url;
	private String icon;
	private Integer sort;
	public List<NavVo> children;
	
	//为满足tree
	private String title; 

	public NavVo() {
	}

	public NavVo(String name) {
		this.name = name;
	}

	public NavVo(String name, Integer pid, String url, String icon, String sort) {
		this.name = name;
		this.pid = pid;
		this.url = url;
		this.icon = icon;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "icon", length = 100)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<NavVo> getChildren() {
		return children;
	}

	public void setChildren(List<NavVo> children) {
		this.children = children;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
