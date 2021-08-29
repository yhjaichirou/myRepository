package com.fgw.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.service.RouterService;
import com.fgw.project.service.UserService;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

/**
 * 后台接口
 * @author yhj
 * @date 2020年12月17日
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Resource
	private RouterService routService;
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public RetKit login(@RequestBody String param) {
		String account = JSONObject.parseObject(param).getString("username");
		String psw = JSONObject.parseObject(param).getString("password");
		String orgId = JSONObject.parseObject(param).getString("orgId");
		return userService.getUser(account,psw,orgId);
	}
	@RequestMapping("/logout")
	public RetKit logout(HttpServletRequest request) {
		String token = request.getHeader("X-Token");
		return userService.logout(token);
	}
	
	@RequestMapping("/userinfo")
	public RetKit userinfo(HttpServletRequest request) {
		String token = request.getHeader("X-Token");
		return userService.getUserOfToken(token);
	}
	
	@RequestMapping("/getRouter")
	public RetKit getRouter(@RequestBody String param) {
		String roleId = JSONObject.parseObject(param).getString("roleId");
		return userService.getRouter(Integer.parseInt(roleId));
	}
	
	
	
	@RequestMapping("/getUserInfo/{userId}")
	public RetKit getUserInfo(@PathVariable Integer userId) {
		return userService.getUserInfo(userId);
	}
	
	@RequestMapping("/list")
	public RetKit list() {
		return RetKit.okData("");
	}
	
	@RequestMapping("/addGroup")
	public RetKit addGroup(Integer userId ) {
//		User u = userService.getGroup(userId);
		return RetKit.ok();
	}
	
	
	/**
	 * 	角色
	 */
	@RequestMapping("/getAllRouters")
	public RetKit getAllRouters() {
		return userService.getAllRouters();
	}
	
	@RequestMapping("/getRoles")
	public RetKit getRoles(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return userService.getRoles(param);
	}
	
	@RequestMapping("/addRole")
	public RetKit addRole(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return userService.addRole(param);
	}
	
	@RequestMapping("/updateRole")
	public RetKit updateRole(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return userService.updateRole(param);
	}
	
	/**
	 * 用户管理
	 */
	@RequestMapping("/getUsers")
	public RetKit getUsers(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return userService.getUsers(param);
	}
	
	@RequestMapping("/getRoleList/{orgId}/{roleId}")
	public RetKit getRoleList(@PathVariable Integer orgId,@PathVariable Integer roleId) {
		return userService.getRoleList(orgId,roleId);
	}
	@RequestMapping("/getOrgList/{loginOrgId}/{roleId}")
	public RetKit getOrgList(@PathVariable Integer loginOrgId,@PathVariable Integer roleId) {
		return userService.getOrgList(loginOrgId,roleId);
	}
	@RequestMapping("/getGroupList/{orgId}")
	public RetKit getGroupList(@PathVariable Integer orgId) {
		return userService.getGroupList(orgId);
	}
	
	@RequestMapping("/addUser")
	public RetKit addUser(@RequestBody String param) {
		return userService.addUser(param);
	}
	
	@RequestMapping("/updateUser")
	public RetKit updateUser(@RequestBody String param) {
		return userService.updateUser(param);
	}
	@RequestMapping("/updateUserPwd")
	public RetKit updateUserPwd(@RequestBody String param) {
		return userService.updateUserPwd(param);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public RetKit deleteUser(@PathVariable String userId) {
		return userService.deleteUser(Integer.parseInt(userId));
	}
	
	
}
