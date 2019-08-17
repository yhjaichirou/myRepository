package com.dzb.partyBranch.model.vo;

import java.util.List;

import com.dzb.partyBranch.model.po.User;

/**
 * 党支部信息view
 * @author yhj
 */
public class PartyBranchVo {
	private Integer id;
	private String partName;
	private Integer userCount;
	private List<User> users;
 
	public PartyBranchVo() {
		
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getPartName() {
		return partName;
	}


	public void setPartName(String partName) {
		this.partName = partName;
	}


	public Integer getUserCount() {
		return userCount;
	}


	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}

}
