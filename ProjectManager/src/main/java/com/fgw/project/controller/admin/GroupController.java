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
import com.fgw.project.util.RetKit;

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
	public RetKit getGroup(@PathParam(value = "orgId") String orgId ) {
		return gService.getGroup(Integer.parseInt(orgId));
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
