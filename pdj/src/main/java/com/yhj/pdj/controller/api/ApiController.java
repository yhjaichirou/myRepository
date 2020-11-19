package com.yhj.pdj.controller.api;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yhj.pdj.kit.EncryptDesUtils;
import com.yhj.pdj.kit.MDateUtil;
import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.kit.StrKit;
import com.yhj.pdj.model.po.PdjAppoint;
import com.yhj.pdj.model.po.PdjRecord;
import com.yhj.pdj.service.ApiService;



@Controller
@RequestMapping("api/record")
@CrossOrigin
public class ApiController {
	
	private Log log = LogFactory.getLog(ApiController.class);

	@Resource
	private ApiService apiService;
	
	@RequestMapping("test")
	@ResponseBody
	public String home(HttpServletRequest  request,HttpServletResponse response) {
		return "hello word!";
	}
	
	//微信预约
	@RequestMapping("appoint")
	@ResponseBody
	public RetKit appoint(String openid,String userName,String idcard,String phone,
			String taskId,String taskName,String createTime,Integer status,String appId,
			String areaId,String areaName,String orderDate) {
		return apiService.appoint( openid, userName, idcard, phone, taskId, taskName, createTime,
				status, appId, areaId, areaName,orderDate);
	}
	
	//微信预约列表
	@RequestMapping("appointList")
	@ResponseBody
	public RetKit appointList(String openid,String taskId) {
		return apiService.appointList(openid, taskId);
	}
	
	//获取业务类型
	@RequestMapping("getTaskTypes")
	@ResponseBody
	public RetKit getTaskTypes() {
		return apiService.getTaskTypes();
	}
	
	//查询预约-废弃
	@RequestMapping("findAppoint")
	@ResponseBody
	public RetKit findAppoint(String jsondata) {
		PdjAppoint pr = JSONObject.parseObject(jsondata, PdjAppoint.class);
		return RetKit.ok();
	}
	
	//更新预约记录
	@RequestMapping("upAppoint")
	@ResponseBody
	public RetKit upAppoint(String appointCode) {
		return apiService.upAppoint( appointCode);
	}
	//加密
	@RequestMapping("encode")
	@ResponseBody
	public RetKit encode(String string,String key) {
		if(StrKit.isBlank(string)) {
			return RetKit.fail("加密内容不能为空！");
		}
		String a  = EncryptDesUtils.encrypt(string,key);
		return RetKit.okData(a);
	}
	//加密
	@RequestMapping("encodeList")
	@ResponseBody
	public RetKit encodeList(String strings,String key) {
		if(StrKit.isBlank(strings)) {
			return RetKit.fail("加密内容不能为空！");
		}
		List<String> lstrings = JSONObject.parseArray(strings, String.class);
		List<String> encodes = new ArrayList<>();
		for (int i = 0; i < lstrings.size(); i++) {
			String code  = EncryptDesUtils.encrypt(lstrings.get(i),key);
			encodes.add(code);
		}
		return RetKit.okData(encodes);
	}
	
	//解密
	@RequestMapping("decode")
	@ResponseBody
	public RetKit decode(String string,String key) {
		if(StrKit.isBlank(string)) {
			return RetKit.fail("解密内容不能为空！");
		}
		try {
			String b = EncryptDesUtils.decrypt(string,key);
			return RetKit.okData(b);
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.okData("解密失败！"+e.getMessage());
		}
	}
	
	@RequestMapping("reporting")
	@ResponseBody
	public RetKit reporting(String jsondata) {
		PdjRecord pr = JSONObject.parseObject(jsondata, PdjRecord.class);
		log.info("接受到的JSON:"+jsondata);
		log.info("解析后的JSONObject:"+pr);
		return apiService.reporting(pr);
	}
	
	@RequestMapping("reporting2")
	@ResponseBody
	public RetKit reporting2(String jsondata) {
		PdjRecord pr = JSONObject.parseObject(jsondata, PdjRecord.class);
		log.info("接受到的JSON:"+jsondata);
		if(StrKit.isBlank(pr.getTaskId()) || StrKit.isBlank(pr.getTaskName()) || StrKit.isBlank(pr.getProManager()) 
			||StrKit.isBlank(pr.getUserName())||StrKit.isBlank(pr.getUserCert())	) {
			return RetKit.fail("参数不正确！");
		}
		return apiService.reporting2(pr);
	}
	
}
