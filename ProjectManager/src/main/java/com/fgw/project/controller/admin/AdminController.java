package com.fgw.project.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.model.po.Menu;
import com.fgw.project.model.po.User;
import com.fgw.project.model.vo.MenuVo;
import com.fgw.project.service.RouterService;
import com.fgw.project.service.UserService;
import com.fgw.project.util.RetKit;

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
		return userService.getUser(account,psw);
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
	
	
	
	@RequestMapping("/getUserInfo")
	public RetKit getUserInfo(Integer userId ) {
		User u = userService.getUserInfo(userId);
		return RetKit.okData(u);
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
	public RetKit getRoles() {
		return userService.getRoles();
	}
	
	@RequestMapping("/addRole")
	public RetKit addRole(@RequestBody String param) {
		return userService.addRole(param);
	}
	
	@RequestMapping("/updateRole")
	public RetKit updateRole(@RequestBody String param) {
		return userService.updateRole(param);
	}
	
	/**
	 * 用户管理
	 */
	@RequestMapping("/getUsers")
	public RetKit getUsers(@PathParam(value = "orgId") String orgId ) {
		return userService.getUsers(orgId);
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
	
	@DeleteMapping("/deleteUser/{userId}")
	public RetKit deleteUser(@PathVariable String userId) {
		return userService.deleteUser(Integer.parseInt(userId));
	}
	
	
}
