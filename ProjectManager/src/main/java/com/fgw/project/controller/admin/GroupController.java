package com.fgw.project.controller.admin;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fgw.project.service.GroupService;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

/**
 * 后台接口
 * @author yhj
 * @date 2020年12月17日
 */
@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {

	@Resource
	private GroupService gService;
	
	@RequestMapping("/getGroup")
	public RetKit getGroup(@RequestBody String param) {
		if(StrKit.isBlank(param)) {
			return RetKit.fail("参数不能为空！");
		}
		return gService.getGroup(param);
	}
	@RequestMapping("/addGroup")
	public RetKit addGroup(@RequestBody String param) {
		return gService.addGroup(param);
	}
	@RequestMapping("/updateGroup")
	public RetKit updateGroup(@RequestBody String param) {
		return gService.updateGroup(param);
	}
	@DeleteMapping("/deleteGroup/{groupId}")
	public RetKit deleteGroup(@PathVariable String groupId) {
		return gService.deleteGroup(Integer.parseInt(groupId));
	}
}
