package com.fgw.project.controller.admin;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fgw.project.service.TaskService;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

/**
 * 后台接口
 * @author yhj
 * @date 2021年9月
 */
@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

	@Resource
	private TaskService tService;
	
	@RequestMapping("/getDbList")
	public RetKit getGroup(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return tService.getDbList(param);
	}
	
	@RequestMapping("/dealDb")
	public RetKit dealDb(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return tService.dealDb(param);
	}
	
}
