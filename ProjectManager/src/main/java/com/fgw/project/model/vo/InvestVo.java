package com.fgw.project.model.vo;

import java.util.Date;

public class InvestVo {

	private Integer id;
	private Date investDate;
	private String investType;
	private String investMoney;
	private Integer proId;
	
	//外键
	private String investDateStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInvestDate() {
		return investDate;
	}

	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}

	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	public String getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(String investMoney) {
		this.investMoney = investMoney;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getInvestDateStr() {
		return investDateStr;
	}

	public void setInvestDateStr(String investDateStr) {
		this.investDateStr = investDateStr;
	}
	
	
	
}
