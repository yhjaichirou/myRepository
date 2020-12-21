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
	
	@RequestMapping("/getPorjects")
	public RetKit getPorjects(@PathParam(value = "orgId") String orgId ) {
		return proService.getPorjects(Integer.parseInt(orgId));
	}
	
}
