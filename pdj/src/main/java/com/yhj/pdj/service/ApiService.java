package com.yhj.pdj.service;

import java.awt.Menu;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhj.pdj.constant.OtherConstant;
import com.yhj.pdj.controller.api.ApiController;
import com.yhj.pdj.kit.BeanKit;
import com.yhj.pdj.kit.MDateUtil;
import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.kit.StrKit;
import com.yhj.pdj.model.po.PdjRecord;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.http.HttpUtil;

@Service
public class ApiService {

	private Log log = LogFactory.getLog(ApiService.class);
	
	public RetKit delUser(List<Integer> ids) {
		try {
			if(Db.use().findAll(Entity.create("pdj_record")).size()<=1) {
				return RetKit.fail("只剩一个用户无法继续删除！");
			}
			Db.use().tx(db -> {
				for (Integer userId : ids) {
					Db.use().del(
						    Entity.create("admin_user_role")
						    .set("user_id", userId)
						);
					Db.use().del(
						    Entity.create("admin_user")
						    .set("id", userId)
						);
				}
			});	
			return RetKit.ok();
		} catch (Exception e) {
			return RetKit.fail(e.getMessage());
		}
		
	}
	
	public String reporting2(PdjRecord pr) {
		try {
			//上报好差评系统
			Map<String, Object> map = new HashMap<>();//BeanKit.objectToMap(pr);
			map.put("areaId", OtherConstant.areaId);
			map.put("areaName", OtherConstant.areaName);
			map.put("taskId", pr.getTaskId());
			map.put("taskName", pr.getTaskName());
			map.put("subMatter", pr.getSubMatter());

			map.put("proStatus", pr.getProStatus());
			map.put("proDepartId", pr.getProDepartId());
			map.put("proDepart", pr.getProDepart());
			map.put("proManager", pr.getProManager()); //一窗受理张萌
			map.put("deptCode", pr.getDeptCode());
			map.put("userName", pr.getUserName());
			map.put("userCert", pr.getUserCert());
			map.put("phonumber", pr.getPhonumber());
			map.put("taskType", pr.getTaskType());
			map.put("certType", pr.getCertType());//身份证：111
			map.put("appId", OtherConstant.appId);
			
//			map.put("projectId", "");
//			map.put("pf", pr.getPf());
//			map.put("proManagerNo", "");
//			map.put("useLevel", pr.getUserCert());
//			map.put("taskHandleItem", "");
//			map.put("apprate", pr.getApprate());// 评价级别   满意非常满意
//			map.put("mouldAp", pr.getMouldAp());
//			map.put("apprateDetail", pr.getApprateDetail());
			
			String result = HttpUtil.get(OtherConstant.ReportingUrl, map);
			log.info(result);
			return result;
		} catch (Exception e) {
			log.error("上报失败！"+e.getMessage());
			return "";
		}
	}
	

	public RetKit reporting(PdjRecord pr) {
		try {
			//上报好差评系统
			Map<String, Object> map = new HashMap<>();//BeanKit.objectToMap(pr);
			map.put("areaId", OtherConstant.areaId);
			map.put("areaName", OtherConstant.areaName);
			map.put("taskId", pr.getTaskId());
			map.put("taskName", pr.getTaskName());
			map.put("subMatter", pr.getSubMatter());

			map.put("proStatus", pr.getProStatus());
			map.put("proDepartId", pr.getProDepartId());
			map.put("proDepart", pr.getProDepart());
			map.put("proManager", pr.getProManager()); //一窗受理张萌
			map.put("deptCode", pr.getDeptCode());
			map.put("userName", pr.getUserName());
			map.put("userCert", pr.getUserCert());
			map.put("phonumber", pr.getPhonumber());
			map.put("taskType", pr.getTaskType());
			map.put("certType", pr.getCertType());//身份证：111
			map.put("appId", OtherConstant.appId);
			
//			map.put("projectId", "");
//			map.put("pf", pr.getPf());
//			map.put("proManagerNo", "");
//			map.put("useLevel", pr.getUserCert());
//			map.put("taskHandleItem", "");
//			map.put("apprate", pr.getApprate());// 评价级别   满意非常满意
//			map.put("mouldAp", pr.getMouldAp());
//			map.put("apprateDetail", pr.getApprateDetail());
			
			String result = HttpUtil.get(OtherConstant.ReportingUrl, map);
			String errorMsg = "";
			log.info("上报好差评系统结果："+result);
			if(StrKit.notBlank(result)) {
				JSONObject obj = JSONObject.parseObject(result);
				if(((String)obj.get("code")).equals("200")) {
					pr.setStatus(1);
				}else {
					pr.setStatus(2);
					errorMsg = "上报失败，"+(String)obj.get("msgerror");
				}
			}else {
				errorMsg = "上报失败，接口返回null";
			}
			
			Db.use().insert(Entity.parse(pr));
			if(pr.getStatus() == 1) {
				return RetKit.ok("上报成功！");
			}else {
				return RetKit.fail(errorMsg);
			}
		} catch (Exception e) {
			log.error("上报失败！"+e.getMessage());
			return RetKit.fail("上报失败！"+e.getMessage());
		}
	}
	
	/**
	 * 	异步 - 上报
	 */
	private void reportingHXP(String dingUserid) {
		Runnable run = () -> {
			try {
				
				
			} catch (Exception e) {
				log.error("网络异常，删除用户信息失败！");
			}
		};
		ThreadUtil.execute(run);
	}

	public RetKit appoint(String openid, String userName, String idcard, String phone, String taskId, String taskName,
			String createTime, Integer status, String appId, String areaId, String areaName) {
		if(StrKit.isBlank(openid) || StrKit.isBlank(idcard) || StrKit.isBlank(userName) || StrKit.isBlank(phone) ) {
			return RetKit.fail("参数不正确");
		}
		
		
		try {
			String dateTimeStr = Long.toString(System.currentTimeMillis(), 100);
			String adc = "" + (char)(Math.random()*26+'a');
			String appointCode = dateTimeStr + adc;
			Date _createTime = MDateUtil.stringToDate(createTime, null);
			Long ids = Db.use().insertForGeneratedKey(
				    Entity.create("user")
				    .set("openid", openid)
				    .set("userName", userName)
				    .set("appoint_code", appointCode)
				    .set("idcard", idcard)
				    .set("phone", phone)
				    .set("taskId", taskId)
				    .set("taskName", taskName)
				    .set("createTime", _createTime)
				    .set("status", 0)
				    .set("appId", StrKit.isBlank(appId)?OtherConstant.appId:appId)
				    .set("areaId", StrKit.isBlank(areaId)?OtherConstant.areaId:areaId)
				    .set("areaName", StrKit.isBlank(areaName)?OtherConstant.areaName:areaName)
			);
			return RetKit.ok("预约成功！预约码:"+appointCode);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("预约失败！错误信息："+e.getMessage());
			return RetKit.fail("预约失败！"+e.getMessage());
		}
	}
	
}
