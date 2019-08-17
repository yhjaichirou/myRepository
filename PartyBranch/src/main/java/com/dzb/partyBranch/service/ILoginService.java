package com.dzb.partyBranch.service;

import com.dzb.partyBranch.kit.RetKit;


public interface ILoginService {
	
	/**
	 * 判断是否存在用户名
	 * @return
	 */
	Boolean hasUserName(String userName);
	
	
	/**
	 * 校验是否用户名密码合法
	 * @return
	 */
	Boolean validatePwdAndName(String userName, String pwd);
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @param remember
	 * @param loginIp
	 * @return
	 */
	RetKit login(String userName, String password, boolean remember, String loginIp);
	
	/**
	 * 记录登录日志
	 * @param userId
	 * @param loginIp
	 */
	void createLoginLog(Integer userId, String loginIp);
	
	/**
	 * 修改密码验证
	 * @param id
	 * @param oldP
	 * @param newP
	 * @return
	 */
	RetKit updataPwd(Integer id,String oldP,String newP);

	/**
	 * 判断是否有权限登录
	 * @param userId
	 * @param roleId
	 * @return
	 */
	boolean isLoginIn(Integer userId, Integer roleId);
}
