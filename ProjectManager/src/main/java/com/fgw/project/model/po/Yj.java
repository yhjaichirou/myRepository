package com.fgw.project.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yj")
public class Yj implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Integer type;
	private String stip;
	private Date time;
	private Integer orgId;
	private String noticePeople;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "stip")
	public String getStip() {
		return stip;
	}

	public void setStip(String stip) {
		this.stip = stip;
	}

	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "org_id")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Column(name = "notice_people")
	public String getNoticePeople() {
		return noticePeople;
	}

	public void setNoticePeople(String noticePeople) {
		this.noticePeople = noticePeople;
	}

	
}
