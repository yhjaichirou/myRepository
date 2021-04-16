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
import com.fgw.project.service.ProjectService;
import com.fgw.project.service.YjService;
import com.fgw.project.util.RetKit;

/**
 * 报表接口
 * @author yhj
 * @date 2020年12月17日
 */
@RestController
@RequestMapping("/reportForm")
@CrossOrigin
public class ReportFormController {

	@Resource
	private ProjectService pService;
	
	@RequestMapping("/getProjectForm")
	public RetKit getProjectForm(@PathParam(value = "pn") Integer pn,@PathParam(value = "ps") Integer ps,@PathParam(value = "orgId") Integer orgId,@PathParam(value = "status") Integer status,@PathParam(value = "search") String search) {
		return pService.getProjectForm(pn,ps,orgId,status,search);
	}
	
	@RequestMapping("/getProjectForm2")
	public RetKit getProjectForm2(@PathParam(value = "pn") Integer pn,@PathParam(value = "ps") Integer ps,@PathParam(value = "orgId") Integer orgId,@PathParam(value = "status") Integer status,@PathParam(value = "search") String search) {
		return pService.getProjectForm2(pn,ps,orgId,status,search);
	}
}
