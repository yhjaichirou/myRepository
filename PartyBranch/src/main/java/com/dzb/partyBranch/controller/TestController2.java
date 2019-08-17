package com.dzb.partyBranch.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.service.IUserService;

@RestController
@RequestMapping("test")

public class TestController2 {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/index")
	public RetKit initView(HttpServletRequest  request,HttpServletResponse response) {
		return RetKit.ok();
	}

}
