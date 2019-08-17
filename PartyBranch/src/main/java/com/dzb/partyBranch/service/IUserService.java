package com.dzb.partyBranch.service;

import java.util.List;

import com.dzb.partyBranch.model.vo.NavVo;
import com.dzb.partyBranch.model.vo.UserVo;

/**
 * 用户服务
 * @author yhj
 *
 */
public interface IUserService {

	/**
	 * 获取用户相关信息
	 */
	public UserVo getUserInfo(Integer userId);
	
	/**
	 * 获取菜单
	 * @param roleId
	 * @return
	 */
	public List<NavVo> getMenus(Integer roleId);
}
