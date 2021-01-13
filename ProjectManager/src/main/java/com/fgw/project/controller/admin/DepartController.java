package com.fgw.project.controller.admin;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fgw.project.service.DepartService;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

/**
 * 	单位管理
 * @author yhj
 * @date 2020年12月17日
 */
@RestController
@RequestMapping("/depart")
@CrossOrigin
public class DepartController {

	@Resource
	private DepartService gService;
	
	@RequestMapping("/getOrgtypes")
	public RetKit getOrgtypes() {
		return gService.getOrgtypes();
	}
	
	@RequestMapping("/getDepart/{orgId}")
	public RetKit getDepart(@PathVariable String orgId ) {
		return gService.getDepart(Integer.parseInt(orgId));
	}
	@RequestMapping("/addDepart")
	public RetKit addDepart(@RequestBody String param) {
		return gService.addDepart(param);
	}
	@RequestMapping("/updateDepart")
	public RetKit updateDepart(@RequestBody String param) {
		return gService.addDepart(param);
	}
	@DeleteMapping("/deleteDepart/{departId}")
	public RetKit deleteDepart(@PathVariable String DepartId) {
		return gService.deleteDepart(Integer.parseInt(DepartId));
	}
	
	
	
	//成员管理
	@RequestMapping("/getPeopleList/{orgId}")
	public RetKit getPeopleList(@PathVariable Integer orgId) {
		return gService.getPeopleList(orgId);
	}
	
	@RequestMapping("/getPeople/{peoId}")
	public RetKit getPeople(@PathVariable Integer peoId ) {
		return gService.getPeople(peoId);
	}
	@RequestMapping("/addOrUpdateDepart")
	public RetKit addOrUpdateDepart(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return gService.addOrUpdateDepart(param);
	}
	@DeleteMapping("/deletePeople/{orgId}")
	public RetKit deletePeople(@PathVariable Integer orgId) {
		return gService.deletePeople(orgId);
	}
}
