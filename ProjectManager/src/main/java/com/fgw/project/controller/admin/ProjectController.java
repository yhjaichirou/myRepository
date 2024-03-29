package com.fgw.project.controller.admin;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.repository.IShbOptionRepository;
import com.fgw.project.service.CommonService;
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
	@Resource
	private CommonService comService;
	@Autowired
	private IShbOptionRepository shbOptionR;
	
	@RequestMapping("/getTzqkList/{projectId}")
	public RetKit getTzqkList(@PathVariable Integer projectId) {
		return proService.getTzqkList(projectId);
	}
	
	@RequestMapping("/getAllMsg/{orgId}")
	public RetKit getAllMsg(@PathVariable Integer orgId) {
		return proService.getAllMsg(orgId);
	}
	
	@RequestMapping("/getAllProject")
	public RetKit getPorjects(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return proService.getAllProject(param);
	}
	
	@RequestMapping("/getAllProjectTask")
	public RetKit getAllProjectTask(@PathParam(value = "pn") Integer pn,@PathParam(value = "ps") Integer ps,
			@PathParam(value = "roleId") Integer roleId,@PathParam(value = "orgId") Integer orgId,@PathParam(value = "status") String status,@PathParam(value = "search") String search) {
		if(pn==null || ps==null) {
			pn = 1;
			ps = 20;
		}
		return proService.getAllProjectTask(pn,ps,roleId,orgId,status,search);
	}
	
	@RequestMapping("/getProject/{projectId}")
	public RetKit getProject(@PathVariable String projectId) {
		if(StrKit.isBlank(projectId)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.getProject(projectId);
	}
	
	@RequestMapping("/getSHBOption")
	public RetKit getSHBOption() {
		return RetKit.okData(shbOptionR.findAllByStatus(1));
	}
	
	@RequestMapping("/getProjectAboutSHB/{projectId}")
	public RetKit getProjectAboutSHB(@PathVariable Integer projectId) {
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return proService.getProjectAboutSHB(projectId);
	}
	
	@RequestMapping("/clickUpdateStatus")
	public RetKit clickUpdateStatus(@RequestBody String param) {
		JSONObject jb = JSONObject.parseObject(param);
		Integer projectId = jb.getInteger("projectId");
		Integer status = jb.getInteger("status");
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return proService.clickUpdateStatus(projectId,status);
	}
	
	@RequestMapping("/getAllFormParam/{orgId}")
	public RetKit getAllFormParam(@PathVariable Integer orgId) {
		return proService.getAllFormParam(orgId);
	}
	@RequestMapping("/getAllOrgs")
	public RetKit getAllOrgs() {
		return proService.getAllOrgs();
	}
	@RequestMapping("/getAllDeparts")
	public RetKit getAllDeparts() {
		return proService.getAllDeparts();
	}
	
	@RequestMapping("/getJoiners/{orgIds}")
	public RetKit getJoiners(@PathVariable String orgIds) {
		return proService.getJoiners(orgIds);
	}
	
	/**
	 * 	获取指定单位下的领导
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/getLeadersOfOrgId/{orgId}")
	public RetKit getLeadersOfOrgId(@PathVariable Integer orgId) {
		return proService.getLeadersOfOrgId(orgId);
	}
	
	@RequestMapping("/getProManagersOfProEnterId/{proEnter}")
	public RetKit getProManagersOfProEnterId(@PathVariable Integer proEnter) {
		return proService.getProManagersOfProEnterId(proEnter);
	}
	
	@RequestMapping("/updateProject")
	public RetKit updateProject(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.addProject(param);
	}
	@RequestMapping("/updateProjectSHB")
	public RetKit updateProjectSHB(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.updateProjectSHB(param);
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
	
	@RequestMapping("/dispatchProject")
	public RetKit dispatchProject(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.dispatchProject(param);
	}
	
	@DeleteMapping("/deleteProject/{projectId}")
	public RetKit deleteProject(@PathVariable Integer projectId) {
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return proService.deleteProject(projectId);
	}
	
	@RequestMapping("/clickShenhe")
	public RetKit clickShenhe(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return proService.clickShenhe(param);
	}
	
	/**
	 * 项目文件
	 * @return
	 */
	@RequestMapping("/getFileList/{projectId}/{pn}/{ps}")
	public RetKit getFileList(@PathVariable Integer projectId,@PathVariable Integer pn,@PathVariable Integer ps) {
		if(projectId==null) {
			return RetKit.fail("参数不正确！");
		}
		return proService.getFileList(projectId,pn,ps);
	}
	
	
	//任务
	@RequestMapping("/getAllTaskList/{projectId}/{typeId}")
	public RetKit getAllTaskList(@PathVariable Integer projectId,@PathVariable Integer typeId) {
		return taskService.getAllTaskList(projectId,typeId);
	}
	@RequestMapping("/getAllCountMap/{projectId}")
	public RetKit getAllCountMap(@PathVariable Integer projectId) {
		return taskService.getAllCountMap(projectId);
	}
	
	@RequestMapping("/getTask/{id}")
	public RetKit getTask(@PathVariable Integer id) {
		return taskService.getTask(id);
	}
	
	/**
	 * 	获取指定单位下的人员
	 * @param orgId
	 * @return
	 */
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
	
	//工作任务
	@DeleteMapping("/fileDelete/{taskId}/{fileId}")
	public RetKit deleteTask(@PathVariable Integer taskId,@PathVariable Integer fileId) {
		if(taskId==null || fileId==null) {
			return RetKit.fail("参数不正确！");
		}
		return taskService.fileDelete(taskId,fileId);
	}
	
	@RequestMapping("/uploadFJ")
	public RetKit deleteTask(@RequestParam("file") MultipartFile file) {
		if(file==null) {
			return RetKit.fail("参数不正确！");
		}
		return comService.uploadFile(file);
	}
	
	@RequestMapping("/confirmTask")
	public RetKit confirmTask(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不正确！");
		}
		return taskService.confirmTask(param);
	}
	
	/**
	 * 任务督办
	 * @param taskId
	 * @return
	 */
	@RequestMapping("/taskDb/{taskId}")
	public RetKit taskDb(@PathVariable Integer taskId) {
		if(taskId==null) {
			return RetKit.fail("参数不正确！");
		}
		return taskService.taskDb(taskId);
	}
	

	/**
	 *  ---------------------  甘特图  -------------------------
	 */
	@RequestMapping("/getProjectGanttData/{projectId}/{querystatus}/{pn}/{ps}")
	public RetKit getProjectGanttData(@PathVariable Integer projectId,@PathVariable Integer querystatus,@PathVariable Integer pn,@PathVariable Integer ps) {
		return proService.getProjectGanttData(projectId,querystatus,pn,ps);
	}
}
