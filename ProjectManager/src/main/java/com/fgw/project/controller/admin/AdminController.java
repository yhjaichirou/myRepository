package com.fgw.project.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.model.po.Menu;
import com.fgw.project.model.po.User;
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
		String account = JSONObject.parseObject(param).getString("account");
		String psw = JSONObject.parseObject(param).getString("psw");
		return userService.getUser(account,psw);
	}
	
	@RequestMapping("/getRouter")
	public RetKit getRouter() {
		List<Menu> routs = routService.getRouter();
		return RetKit.okData(routs);
	}
	
	@RequestMapping("/getUserInfo")
	public RetKit getUserInfo(Integer userId ) {
		User u = userService.getUserInfo(userId);
		return RetKit.okData(u);
	}
}
