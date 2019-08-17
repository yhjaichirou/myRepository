package com.dzb.partyBranch.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dzb.partyBranch.constant.RoleConstant;
import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.po.Area;
import com.dzb.partyBranch.model.po.User;
import com.dzb.partyBranch.model.vo.UserAuthentic;
import com.dzb.partyBranch.service.IConstructionService;


@Controller
@RequestMapping("construction")
public class ConstructionController {
	
	@Resource
	private IConstructionService conService;
	
	@RequestMapping("index")
	public String initView(HttpServletRequest  request,HttpServletResponse response) {
		UserAuthentic user = (UserAuthentic)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user.getRoleId().equals(RoleConstant.ADMIN)) {
			if(!user.getAreaId().equals(0)) {
				Area area = conService.getAreaById(user.getAreaId());
				boolean isPrimaryAll = area.getPrimaryall();//是否是区域中心，可以管理各旗县
				if(isPrimaryAll) {
					List<Area> areas = conService.getAreas();
					request.setAttribute("areas", areas);
				}
				request.setAttribute("types", conService.getTypes());
			}
		}
		request.setAttribute("enters", conService.getEnters());
		return "/views/app/construction/index";
	}
	
	@RequestMapping("info")
	public String info(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/construction/sss";
	}
	@RequestMapping("add")
	public String add(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/construction/addOrUpdate";
	}
	
	@RequestMapping("getEnterPrises")
	@ResponseBody
	public RetKit getEnterPrises(Integer areaId,Integer typeId) {
		return RetKit.okData(conService.getEnterPrises(areaId,typeId));
	}
	
	@GetMapping("getEnterBranchList")
	@ResponseBody
	public RetKit getEnterPriseList(@PathParam("limit")Integer limit,@PathParam("page")Integer page,@PathParam("enterId")Integer enterId) {
		Sort sort = JpaSort.by(Direction.ASC, "id");
		Map<String, Object> map = conService.getEnterBranchList(page-1,limit,sort,enterId);
		return RetKit.ok_table_data(map.get("list"),(Integer)map.get("count"));
	}

	@RequestMapping("pel")
	public String pel(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/construction/pel";
	}
	@RequestMapping("area")
	public String area(HttpServletRequest  request,HttpServletResponse response) {
		return "/views/app/construction/area";
	}

	@RequestMapping("pel/add")
	public List<User> addUser(HttpServletRequest  request,HttpServletResponse response) {
		List<User> users = new ArrayList<>();
		return users;
	}
}
