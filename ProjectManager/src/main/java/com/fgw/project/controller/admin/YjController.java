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
import com.fgw.project.service.YjService;
import com.fgw.project.util.RetKit;

/**
 * 后台接口
 * @author yhj
 * @date 2020年12月17日
 */
@RestController
@RequestMapping("/yj")
@CrossOrigin
public class YjController {

	@Resource
	private YjService yService;
	
	@RequestMapping("/getYjs")
	public RetKit getPorjects(@PathParam(value = "orgId") Integer orgId,@PathParam(value = "status") Integer status,@PathParam(value = "search") String search) {
		return yService.getYjs(orgId,status,search);
	}
}
