package com.yhj.pdj.controller.api;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
public class ApiController {
	
	private Log log = LogFactory.getLog(ApiController.class);

	@Resource
	private ApiService apiService;
	
	@RequestMapping("test")
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
	
	//查询预约
	@RequestMapping("findAppoint")
	@ResponseBody
	public RetKit findAppoint(String jsondata) {
		PdjAppoint pr = JSONObject.parseObject(jsondata, PdjAppoint.class);
		return RetKit.ok();
	}
	
	//更新预约记录
	@RequestMapping("upAppoint")
	@ResponseBody
	public RetKit upAppoint(String openid,String appointCode) {
		String a = "密文";
		return RetKit.okData(a);
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
	public RetKit reporting2(String jsondata) {
		PdjRecord pr = JSONObject.parseObject(jsondata, PdjRecord.class);
		log.info("接受到的JSON:"+jsondata);
		log.info("解析后的JSONObject:"+pr);
		return apiService.reporting2(pr);
	}
	
}
