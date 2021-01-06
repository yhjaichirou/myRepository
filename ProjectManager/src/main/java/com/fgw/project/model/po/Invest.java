package com.fgw.project.model.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invest")
public class Invest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date investDate;
	private String investType;
	private String investMoney;
	private Integer proId;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "invest_date")
	public Date getInvestDate() {
		return investDate;
	}

	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}

	@Column(name = "invest_type")
	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}
	
	@Column(name = "invest_money")
	public String getInvestMoney() {
		return investMoney;
	}

	public void setInvestMoney(String investMoney) {
		this.investMoney = investMoney;
	}

	@Column(name = "pro_id")
	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	
}
