package com.dzb.partyBranch.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.Menu;
import com.dzb.partyBranch.model.vo.NavVo;
import com.dzb.partyBranch.service.ISystemService;
import com.dzb.partyBranch.service.IUserService;

@Controller
@RequestMapping("system")
public class SystemController {
	
	@Resource
	private ISystemService systemService;
	@Resource
	private IUserService userService;
	
	@RequestMapping("index")
	public String initView(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/system/index";
	}
	@RequestMapping("temple")
	public String temple(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/system/temple";
	}
	@RequestMapping("menu")
	public String menu(HttpServletRequest  request,HttpServletResponse response) {
		List<NavVo> menus = userService.getMenus(0);
		request.setAttribute("allMenus", menus);
		return "/views/app/system/menu";
	}
	
	@RequestMapping("role")
	public String role(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/system/role";
	}
	
	
	@RequestMapping("menu/addorupdate")
	@ResponseBody
	public RetKit addOrUpdateMenu(String name,String icon ,String url , Integer pid,Integer id,Integer sort) {
//		String menuStr = request.getParameter("menu");
//		NavVo nav = (NavVo)JSONObject.parse(menuStr);
//		return systemService.addOrUpdateMenu(nav);
		Menu menu = new Menu();
		if(id!=0) {
			menu.setId(id);
		}
		menu.setIcon(icon);
		menu.setName(name);
		menu.setUrl(url);
		menu.setPid(pid);
		menu.setSort(sort);
		return systemService.addOrUpdateMenu(menu);
	}
	
	@RequestMapping("menu/del")
	@ResponseBody
	public RetKit delMenu(Integer id) {
		return systemService.delMenu(id);
	}

	
	@RequestMapping("role/addorupdate")
	@ResponseBody
	public RetKit addOrUpdateRole(String name,String icon ,String url , Integer pid,Integer id,Integer sort) {
//		String menuStr = request.getParameter("menu");
//		NavVo nav = (NavVo)JSONObject.parse(menuStr);
//		return systemService.addOrUpdateMenu(nav);
		Menu menu = new Menu();
		if(id!=0) {
			menu.setId(id);
		}
		menu.setIcon(icon);
		menu.setName(name);
		menu.setUrl(url);
		menu.setPid(pid);
		menu.setSort(sort);
		return systemService.addOrUpdateMenu(menu);
	}
	
	@RequestMapping("role/del")
	@ResponseBody
	public RetKit delRole(Integer id) {
		return systemService.delMenu(id);
	}
}
