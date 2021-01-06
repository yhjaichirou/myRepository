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
import com.fgw.project.service.TaskService;
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
	@Resource
	private TaskService taskService;
	
	@RequestMapping("/getTzqkList/{projectId}")
	public RetKit getTzqkList(@PathVariable Integer projectId) {
		return proService.getTzqkList(projectId);
	}
	
	@RequestMapping("/getAllMsg/{orgId}")
	public RetKit getAllMsg(@PathVariable Integer orgId) {
		return proService.getAllMsg(orgId);
	}
	
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
	
	@RequestMapping("/getProjectAboutSHB/{projectId}")
	public RetKit getProjectAboutSHB(@PathVariable Integer projectId) {
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return proService.getProjectAboutSHB(projectId);
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
	@RequestMapping("/getAllOrgs")
	public RetKit getAllOrgs() {
		return proService.getAllOrgs();
	}
	
	@RequestMapping("/getJoiners/{orgIds}")
	public RetKit getJoiners(@PathVariable String orgIds) {
		return proService.getJoiners(orgIds);
	}
	
	@RequestMapping("/updateProject")
	public RetKit updateProject(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.addProject(param);
	}
	@RequestMapping("/addProject")
	public RetKit addProject(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.addProject(param);
	}
	@RequestMapping("/authProject/{projectId}")
	public RetKit authProject(@PathVariable Integer projectId) {
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return proService.authProject(projectId);
	}
	
	@DeleteMapping("/deleteProject/{projectId}")
	public RetKit deleteProject(@PathVariable Integer projectId) {
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return proService.deleteProject(projectId);
	}
	
	
	//任务
	@RequestMapping("/getAllTaskList/{projectId}/{typeId}")
	public RetKit getAllTaskList(@PathVariable Integer projectId,@PathVariable Integer typeId) {
		return taskService.getAllTaskList(projectId,typeId);
	}
	@RequestMapping("/getTask/{projectId}")
	public RetKit getTask(@PathVariable Integer projectId) {
		return taskService.getTask(projectId);
	}
	@RequestMapping("/getExecutorList/{orgId}")
	public RetKit getExecutorList(@PathVariable Integer orgId) {
		return taskService.getExecutorList(orgId);
	}
	
	
	@RequestMapping("/getAllTaskFormParam/{projectId}")
	public RetKit getAllTaskFormParam(@PathVariable Integer projectId) {
		return taskService.getAllTaskFormParam(projectId);
	}

	@RequestMapping("/updateTask")
	public RetKit updateTask(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return taskService.addTask(param);
	}
	@RequestMapping("/addTask")
	public RetKit addTask(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return taskService.addTask(param);
	}
	
	@DeleteMapping("/deleteTask/{projectId}")
	public RetKit deleteTask(@PathVariable Integer projectId) {
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return taskService.deleteTask(projectId);
	}
	
	
	@RequestMapping("/getAllTasksOfProject/{orgId}/{projectId}")
	public RetKit getAllTasksOfProject(@PathVariable Integer orgId,@PathVariable Integer projectId) {
		return taskService.getAllTasksOfProject(orgId,projectId);
	}
	
	@RequestMapping("/getAllTaskMyList/{orgId}/{projectId}")
	public RetKit getAllTaskMyList(@PathVariable Integer orgId,@PathVariable Integer projectId) {
		return taskService.taskMyList(orgId,projectId);
	}
	
	
	
}
