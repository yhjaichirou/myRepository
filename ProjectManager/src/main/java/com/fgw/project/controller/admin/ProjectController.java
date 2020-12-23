package com.fgw.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.service.GroupService;
import com.fgw.project.service.ProjectService;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

/**
 * 项目接口
 * @author yhj
 * @date 2020年12月17日
 */
@RestController
@RequestMapping("/project")
@CrossOrigin
public class ProjectController {

	@Resource
	private ProjectService proService;
	
	@RequestMapping("/getAllProject")
	public RetKit getPorjects(@PathParam(value = "orgId") Integer orgId,@PathParam(value = "status") String status,@PathParam(value = "search") String search) {
		return proService.getAllProject(orgId,status,search);
	}
	
	@RequestMapping("/getProject/{projectId}")
	public RetKit getProject(@PathVariable String projectId) {
		if(StrKit.isBlank(projectId)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.getProject(projectId);
	}
	
	@RequestMapping("/clickUpdateStatus/{projectId}")
	public RetKit clickUpdateStatus(@PathVariable String projectId) {
		if(StrKit.isBlank(projectId)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.clickUpdateStatus(projectId);
	}
	
	@RequestMapping("/getAllFormParam/{orgId}")
	public RetKit getAllFormParam(@PathVariable Integer orgId) {
		return proService.getAllFormParam(orgId);
	}
	@RequestMapping("/getJoiners/{orgIds}")
	public RetKit getJoiners(@PathVariable String orgIds) {
		return proService.getJoiners(orgIds);
	}
	
	
	@RequestMapping("/addProject")
	public RetKit addGroup(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.addProject(param);
	}
	
	
}
