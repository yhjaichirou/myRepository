package com.dzb.partyBranch.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dzb.partyBranch.service.IUserService;

@Controller
@RequestMapping("eduTrain")
public class EduTrainController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("index")
	public String initView(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/eduTrain/index";
	}
	
	@RequestMapping("info")
	public String info(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/activity/index";
	}

}
