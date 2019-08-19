package com.dzb.partyBranch.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.Area;
import com.dzb.partyBranch.model.po.PartyBranch;
import com.dzb.partyBranch.service.IConstructionService;
import com.dzb.partyBranch.service.IOrganizationService;

@Controller
@RequestMapping("organization")
public class OrganizationController {

	@Resource
	private IConstructionService conService;
	@Resource
	private IOrganizationService service;

	@RequestMapping("index")
	public String initView(HttpServletRequest request, HttpServletResponse response) {
		return "/views/app/organization/index";
	}

	/**
	 * 区域管理
	 * @return
	 */
	@RequestMapping("area")
	public String area(HttpServletRequest request, HttpServletResponse response) {
		// 暂未涉及其他城市
		// request.setAttribute("areas", citys);
		return "/views/app/organization/area";
	}

	/**
	 * 架构总览
	 * @return
	 */
	@RequestMapping("overview")
	public String overview(HttpServletRequest request, HttpServletResponse response) {
		
		return "/views/app/organization/overview";
	}

	/**
	 * 
	 * 单位管理入口
	 * @return
	 */
	@RequestMapping("enterprise")
	public String enterprise(HttpServletRequest request, HttpServletResponse response) {
		List<Area> areas = conService.getAreas();
		request.setAttribute("types", conService.getTypes());
		request.setAttribute("areas", areas);
		return "/views/app/organization/enterprise";
	}

	/**
	 * 区域管理
	 */

	@GetMapping("getAreaList")
	@ResponseBody
	public RetKit getAreaList(@PathParam("limit") Integer limit, @PathParam("page") Integer page,
			@PathParam("cityId") Integer cityId) {
		Sort sort = JpaSort.by(Direction.ASC, "id");
		Map<String, Object> map = service.getAreas(page - 1, limit, sort, cityId);
		return RetKit.ok_table_data(map.get("list"), (Integer) map.get("count"));
	}

	@RequestMapping("area/addorupdate")
	@ResponseBody
	public RetKit addOrUpdate(Integer id, HttpServletRequest request, HttpServletResponse response) {
		String isPrimary = request.getParameter("isPrimary");
		String name = request.getParameter("name");
		return service.addOrUpdate(id, isPrimary, name);
	}
	@RequestMapping("area/del")
	@ResponseBody
	public RetKit delArea(Integer id, HttpServletRequest request, HttpServletResponse response) {
		return service.delArea(id);
	}

	/**
	 * 单位管理
	 */

	@GetMapping("getEntersList")
	@ResponseBody
	public RetKit getEntersList(@PathParam("limit") Integer limit, @PathParam("page") Integer page,
			@PathParam("areaId") Integer areaId,@PathParam("typeId") Integer typeId) {
		Sort sort = JpaSort.by(Direction.ASC, "id");
		Map<String, Object> map = service.getEntersList(page - 1, limit, sort, areaId,typeId);
		return RetKit.ok_table_data(map.get("list"), (Integer) map.get("count"));
	}

	@RequestMapping("enterprise/addorupdate")
	@ResponseBody
	public RetKit addOrUpdateEnters(Integer id, HttpServletRequest request, HttpServletResponse response) {
		String isPrimary = request.getParameter("isPrimary");
		String name = request.getParameter("name");
		return service.addOrUpdateEnters(id, isPrimary, name);
	}
	@RequestMapping("enterprise/del")
	@ResponseBody
	public RetKit delEnter(Integer id, HttpServletRequest request, HttpServletResponse response) {
		return service.delEnter(id);
	}
	
	@RequestMapping("enterprise/getBranchsByEnters")
	@ResponseBody
	public List<PartyBranch> getBranchsByEnters(Integer enterId) {
		return service.getBranchsByEnters(enterId);
	}
	
	
	/**
	 * 架构总览
	 */
	
	@RequestMapping("overview/ss")
	public String overviews(HttpServletRequest request, HttpServletResponse response) {
		return "/views/app/organization/overview/ss";
	}
}
