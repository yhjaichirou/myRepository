package com.yhj.pdj.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.service.RecordService;
import com.yhj.pdj.service.UserService;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

/**
 * 	用户管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("record")
public class RecordController {

	@Resource
	private RecordService recordService;
	
	@RequestMapping("record")
	public String list(HttpServletRequest  request,HttpServletResponse response) {
		return "/cat/record/record";
	}

	
	
	@RequestMapping("record/getRecordList")
	@ResponseBody
	public RetKit getUserList(@PathParam("limit")Integer limit,@PathParam("page")Integer page,
		@PathParam("type")Integer type, @PathParam("startTime") String startTime,@PathParam("endTime") String endTime,
		@PathParam("status") Integer status) {
		Map<String, Object> map = recordService.getRecordrList(page-1,limit,type,status,startTime,endTime);
		return RetKit.ok_table_data(map.get("list"),(Integer)map.get("count"));
	}
	
	/**
	 * 	上报
	 * @param ids
	 * @return
	 */
	@RequestMapping("record/upload")
	@ResponseBody
	public RetKit del(String ids) {
		List<Integer> _ids = JSONObject.parseArray(ids, Integer.class);
		return recordService.upload(_ids);
	}
	
	
	

	
	
}
