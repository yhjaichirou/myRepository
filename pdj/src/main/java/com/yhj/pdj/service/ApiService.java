package com.yhj.pdj.service;

import java.awt.Menu;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	public RetKit reporting(PdjRecord pr) {
		try {
			//上报好差评系统
			Map<String, Object> map = new HashMap<>();//BeanKit.objectToMap(pr);
			map.put("areaId", OtherConstant.areaId);
			map.put("areaName", OtherConstant.areaName);
//			map.put("taskId", value);
//			map.put("taskName", value);
//			map.put("subMatter", value);
//			map.put("projectId", value);
//			map.put("proStatus", value);
//			map.put("proDepartId", value);
//			map.put("proDepart", value);
//			map.put("proManager", value);
//			map.put("pf", value);
//			map.put("proManagerNo", value);
//			map.put("deptCode", value);
			
			
			
			
			
			
			
			
			
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
	
}
