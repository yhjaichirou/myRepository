package com.fgw.project.controller.admin;

import javax.annotation.Resource;

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
	
	@RequestMapping("/getOrgtypes/{orgId}")
	public RetKit getOrgtypes(@PathVariable Integer orgId) {
		return gService.getOrgtypes(orgId);
	}
	
	@RequestMapping("/getUpOrgs/{cpropery}")
	public RetKit getUpOrgs(@PathVariable Integer cpropery) {
		return gService.getUpOrgs(cpropery);
	}
	
//	@RequestMapping("/getDepartList/{orgId}/{pn}/{ps}/{searchContent}/{searchStatus}")
//	public RetKit getDepartList(@PathVariable Integer orgId ,@PathVariable Integer pn ,@PathVariable Integer ps,@PathVariable String searchContent ,@PathVariable Integer searchStatus ) {
//		if(pn==null || ps==null) {
//			pn = 1;
//			ps = 20;
//		}
//		return gService.getDepartList(orgId,pn,ps,searchContent,searchStatus);
//	}
	
	@RequestMapping("/getDepartList")
	public RetKit getDepartList2(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return gService.getDepartList(param);
	}
	
	@RequestMapping("/importXls")
	public RetKit importXls(@RequestBody String param) {
		return gService.importXls(param);
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
	public RetKit deleteDepart(@PathVariable Integer departId) {
		return gService.deleteDepart(departId);
	}
	
	
	
	//成员管理
	@RequestMapping("/getPeopleList")
	public RetKit getPeopleList(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return gService.getPeopleList(param);
	}
	
	@RequestMapping("/importPelXls")
	public RetKit importPelXls(@RequestBody String param) {
		return gService.importPelXls(param);
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
