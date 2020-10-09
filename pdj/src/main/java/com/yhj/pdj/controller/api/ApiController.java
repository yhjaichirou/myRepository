package com.yhj.pdj.controller.api;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.model.po.PdjRecord;
import com.yhj.pdj.service.ApiService;



@RestController
@RequestMapping("api/record")
public class ApiController {
	
	private Log log = LogFactory.getLog(ApiController.class);

	@Resource
	private ApiService apiService;
	
	@RequestMapping("test")
	public String home(HttpServletRequest  request,HttpServletResponse response) {
		return "hello word!";
	}
	
	
	@RequestMapping("reporting")
	public RetKit reporting(String jsondata) {
		PdjRecord pr = JSONObject.parseObject(jsondata, PdjRecord.class);
		log.info("接受到的JSON:"+jsondata);
		log.info("解析后的JSONObject:"+pr);
		return apiService.reporting(pr);
	}
}
