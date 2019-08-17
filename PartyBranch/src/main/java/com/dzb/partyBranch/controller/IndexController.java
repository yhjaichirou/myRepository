package com.dzb.partyBranch.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dzb.partyBranch.model.vo.NavVo;
import com.dzb.partyBranch.model.vo.UserAuthentic;
import com.dzb.partyBranch.service.IUserService;

@Controller
@RequestMapping("index")
public class IndexController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("init")
	public String initView(HttpServletRequest  request,HttpServletResponse response) {
		UserAuthentic user = (UserAuthentic)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<NavVo> menus = userService.getMenus(user.getRoleId());
		request.setAttribute("navList", menus);
		return "/views/index";
	}
	
	@RequestMapping("info")
	public String info(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/set/user/info";
	}
	
	@RequestMapping("console")
	public String console(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/home/console";
	}

}
