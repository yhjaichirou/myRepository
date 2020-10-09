package com.yhj.pdj.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.service.UserService;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

/**
 * 	用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("list")
	public String list(HttpServletRequest  request,HttpServletResponse response) {
		return "/cat/user/list";
	}
	//充值申请
	@RequestMapping("apply")
	public String apply(HttpServletRequest  request,HttpServletResponse response) {
		return "/cat/user/apply";
	}
	//留言列表
	@RequestMapping("leave")
	public String leave(HttpServletRequest  request,HttpServletResponse response) {
		return "/cat/user/leave";
	}
	
	
	@RequestMapping("list/getUserList")
	@ResponseBody
	public RetKit getUserList(@PathParam("limit")Integer limit,@PathParam("page")Integer page,
		@PathParam("account")String account,@PathParam("recommendUser") String recommendUser, @PathParam("isAuth")
		Integer isAuth,  @PathParam("isEnabled")Integer isEnabled,  @PathParam("isLock")Integer isLock,  @PathParam("level")Integer level) {
		Map<String, Object> map = userService.getUserList(page-1,limit,account,recommendUser,isAuth,isEnabled,isLock,level);
		return RetKit.ok_table_data(map.get("list"),(Integer)map.get("count"));
	}
	
	@RequestMapping("list/del")
	@ResponseBody
	public RetKit del(String ids) {
		List<Integer> _ids = JSONObject.parseArray(ids, Integer.class);
		return userService.del(_ids);
	}
	
	@RequestMapping("list/isAuth")
	@ResponseBody
	public RetKit isAuth(Integer id,Integer status) {
		return userService.isAuth(id,status);
	}
	@RequestMapping("list/isEnabled")
	@ResponseBody
	public RetKit isEnabled(Integer id,Integer status) {
		return userService.isEnabled(id,status);
	}
	@RequestMapping("list/isLock")
	@ResponseBody
	public RetKit isLock(Integer id,Integer status) {
		return userService.isLock(id,status);
	}
	
	

	
	
}
