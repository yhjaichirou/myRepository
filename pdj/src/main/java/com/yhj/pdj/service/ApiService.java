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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yhj.pdj.constant.OtherConstant;
import com.yhj.pdj.controller.api.ApiController;
import com.yhj.pdj.kit.BeanKit;
import com.yhj.pdj.kit.MDateUtil;
import com.yhj.pdj.kit.RetKit;
import com.yhj.pdj.kit.StrKit;
import com.yhj.pdj.model.po.HttpRequestVo;
import com.yhj.pdj.model.po.PdjAppoint;
import com.yhj.pdj.model.po.PdjRecord;
import com.yhj.pdj.model.po.PdjTask;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpUtil;

@Service
public class ApiService {

	@Value("${uploadPath}")
	private String uploadPath;
	
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
	
	public RetKit reporting2(PdjRecord pr) {
		try {
			
			//上报好差评系统
			Map<String, Object> map = new HashMap<>();//BeanKit.objectToMap(pr);
			map.put("areaId", OtherConstant.areaId);
			map.put("areaName", OtherConstant.areaName);
			map.put("taskId", pr.getTaskId());
			map.put("taskType", StrKit.isBlank(pr.getTaskType())?"1":pr.getTaskType());//及办件 =1 ，承诺办件=2  默认传1
			map.put("taskName", pr.getTaskName());
			map.put("subMatter", StrKit.isBlank(pr.getSubMatter())?"1":pr.getSubMatter());//申请人类型（1 自然人  2 企业法人 3事业法人  4 社会组织法人  5非法人企业  6 行政机关    9其他组织）  (传默认值 1 )

			map.put("proStatus", StrKit.isBlank(pr.getProStatus())?"3":pr.getProStatus());//1 受理  ， 2 已受理  3已办结 取号为受理 呼叫为已受理 评价为办结是否可以这么理解(可以)
			map.put("proDepartId", OtherConstant.proDepartId);//受理部门编码
			map.put("proDepart", OtherConstant.proDepart);//受理部门
			map.put("deptCode", OtherConstant.deptCode);//受理部门信用代码
			map.put("proManager", pr.getProManager()); //经办人姓名  窗口人员姓名
		
			map.put("userName", pr.getUserName());
			map.put("userCert", pr.getUserCert());
			map.put("phonumber", StrKit.isBlank(pr.getPhonumber())?"":pr.getPhonumber());//暂时不传 电话号码
			map.put("certType", StrKit.isBlank(pr.getCertType())?"111":pr.getCertType());//身份证：111
			map.put("appId", OtherConstant.appId);
			
			map.put("projectId", "");
			map.put("pf", "4");//pr.getPf()
			map.put("proManagerNo", "");
			map.put("useLevel", "");
			map.put("taskHandleItem", "");
			map.put("apprate", StrKit.isBlank(pr.getApprate())?4:pr.getApprate());// 评价级别  默认不评价：4   （5：非常满意 4满意，3基本满意，2不满意，1非常不满意）
			map.put("mouldAp", StrKit.isBlank(pr.getMouldAp())?"":pr.getMouldAp());//评价选项
			map.put("apprateDetail", StrKit.isBlank(pr.getApprateDetail())?"":pr.getApprateDetail());
			
			
			
			Db.use().insert(
			    Entity.create("pdj_record")
			    .set("area_id", OtherConstant.areaId)
			    .set("area_name",OtherConstant.areaName)
			    .set("task_id", pr.getTaskId())
			    .set("task_type", StrKit.isBlank(pr.getTaskType())?"1":pr.getTaskType())
			    .set("task_name",pr.getTaskName())
			    .set("sub_matter", StrKit.isBlank(pr.getSubMatter())?"1":pr.getSubMatter())
			    .set("pro_status", StrKit.isBlank(pr.getProStatus())?"3":pr.getProStatus())
			    .set("pro_depart_id", OtherConstant.proDepartId)
			    .set("pro_depart", OtherConstant.proDepart)
			    .set("dept_code", OtherConstant.deptCode)
			    .set("pro_manager", pr.getProManager())
			    
			    .set("user_name", pr.getUserName())
			    .set("user_cert", pr.getUserCert())
			    .set("phonumber", StrKit.isBlank(pr.getPhonumber())?"":pr.getPhonumber())
			    .set("cert_type", StrKit.isBlank(pr.getCertType())?"111":pr.getCertType())
			    .set("app_id",  OtherConstant.appId)
			    
			    .set("project_id", "")
			    .set("pf", "4")
			    .set("pro_manager_no", "")
			    .set("use_level", "")
			    .set("task_handle_item", "")
			    .set("apprate", StrKit.isBlank(pr.getApprate())?4:pr.getApprate())
			    .set("mould_ap", StrKit.isBlank(pr.getMouldAp())?"":pr.getMouldAp())
			    .set("apprate_detail", StrKit.isBlank(pr.getApprateDetail())?"":pr.getApprateDetail())
			    
			    .set("record_code", pr.getRecordCode())
			    .set("create_time",new Date())
			    .set("status", 1)//上报状态 1 成功 2失败
			);
			
			return RetKit.ok("上报成功！");
			
//			String result = HttpUtil.get(OtherConstant.ReportingUrl, map);
//			log.info("上报好差评系统结果："+result);
//			HttpRequestVo r = JSONObject.parseObject(result, HttpRequestVo.class);
//			if("200".equals(r.getState())) {
//				Db.use().insert(
//					    Entity.create("pdj_record")
//					    .set("area_id", OtherConstant.areaId)
//					    .set("area_name",OtherConstant.areaName)
//					    .set("task_id", pr.getTaskId())
//					    .set("task_type", StrKit.isBlank(pr.getTaskType())?"1":pr.getTaskType())
//					    .set("task_name",pr.getTaskName())
//					    .set("sub_matter", StrKit.isBlank(pr.getSubMatter())?"1":pr.getSubMatter())
//					    .set("pro_status", StrKit.isBlank(pr.getProStatus())?"3":pr.getProStatus())
//					    .set("pro_depart_id", OtherConstant.proDepartId)
//					    .set("pro_depart", OtherConstant.proDepart)
//					    .set("dept_code", OtherConstant.deptCode)
//					    .set("pro_manager", pr.getProManager())
//					    
//					    .set("user_name", pr.getUserName())
//					    .set("user_cert", pr.getUserCert())
//					    .set("phonumber", StrKit.isBlank(pr.getPhonumber())?"":pr.getPhonumber())
//					    .set("cert_type", StrKit.isBlank(pr.getCertType())?"111":pr.getCertType())
//					    .set("app_id",  OtherConstant.appId)
//					    
//					    .set("project_id", "")
//					    .set("pf", "4")
//					    .set("pro_manager_no", "")
//					    .set("use_level", "")
//					    .set("task_handle_item", "")
//					    .set("apprate", StrKit.isBlank(pr.getApprate())?4:pr.getApprate())
//					    .set("mould_ap", StrKit.isBlank(pr.getMouldAp())?"":pr.getMouldAp())
//					    .set("apprate_detail", StrKit.isBlank(pr.getApprateDetail())?"":pr.getApprateDetail())
//					    
//					    .set("record_code", pr.getRecordCode())
//					    .set("create_time", MDateUtil.stringToDate( pr.getCreateTimeStr(), null))
//					    .set("status", 1)//上报状态 1 成功 2失败
//				);
//				return RetKit.ok("上报成功！");
//			}else {
//				Db.use().insert(
//						  Entity.create("pdj_record")
//						    .set("area_id", OtherConstant.areaId)
//						    .set("area_name",OtherConstant.areaName)
//						    .set("task_id", pr.getTaskId())
//						    .set("task_type", StrKit.isBlank(pr.getTaskType())?"1":pr.getTaskType())
//						    .set("task_name",pr.getTaskName())
//						    .set("sub_matter", StrKit.isBlank(pr.getSubMatter())?"1":pr.getSubMatter())
//						    .set("pro_status", StrKit.isBlank(pr.getProStatus())?"3":pr.getProStatus())
//						    .set("pro_depart_id", OtherConstant.proDepartId)
//						    .set("pro_depart", OtherConstant.proDepart)
//						    .set("dept_code", OtherConstant.deptCode)
//						    .set("pro_manager", pr.getProManager())
//						    
//						    .set("user_name", pr.getUserName())
//						    .set("user_cert", pr.getUserCert())
//						    .set("phonumber", StrKit.isBlank(pr.getPhonumber())?"":pr.getPhonumber())
//						    .set("cert_type", StrKit.isBlank(pr.getCertType())?"111":pr.getCertType())
//						    .set("app_id",  OtherConstant.appId)
//						    
//						    .set("project_id", "")
//						    .set("pf", "4")
//						    .set("pro_manager_no", "")
//						    .set("use_level", "")
//						    .set("task_handle_item", "")
//						    .set("apprate", StrKit.isBlank(pr.getApprate())?4:pr.getApprate())
//						    .set("mould_ap", StrKit.isBlank(pr.getMouldAp())?"":pr.getMouldAp())
//						    .set("apprate_detail", StrKit.isBlank(pr.getApprateDetail())?"":pr.getApprateDetail())
//						    
//						    .set("record_code", pr.getRecordCode())
//						    .set("create_time", MDateUtil.stringToDate( pr.getCreateTimeStr(), null))
//						    .set("status", 0)//上报状态 1 成功 2失败
//				);
//				return RetKit.fail("上报失败！"+r.getError());
//			}
			
			
		} catch (Exception e) {
			log.error("上报失败！"+e.getMessage());
			return RetKit.fail("上报失败！"+e.getMessage());
		}
	}
	

	public RetKit reporting(PdjRecord pr) {
		try {
			//先插入后更新
//			Long lid = Db.use().insertForGeneratedKey(
//				    Entity.create("pdj_record")
//				    .set("area_id", OtherConstant.areaId)
//				    .set("area_name",OtherConstant.areaName)
//				    .set("task_id", pr.getTaskId())
//				    .set("task_type", StrKit.isBlank(pr.getTaskType())?"1":pr.getTaskType())
//				    .set("task_name",pr.getTaskName())
//				    .set("sub_matter", StrKit.isBlank(pr.getSubMatter())?"1":pr.getSubMatter())
//				    .set("pro_status", StrKit.isBlank(pr.getProStatus())?"3":pr.getProStatus())
//				    .set("pro_depart_id", OtherConstant.proDepartId)
//				    .set("pro_depart", OtherConstant.proDepart)
//				    .set("dept_code", OtherConstant.deptCode)
//				    .set("pro_manager", pr.getProManager())
//				    
//				    .set("user_name", pr.getUserName())
//				    .set("user_cert", pr.getUserCert())
//				    .set("phonumber", StrKit.isBlank(pr.getPhonumber())?"":pr.getPhonumber())
//				    .set("cert_type", StrKit.isBlank(pr.getCertType())?"111":pr.getCertType())
//				    .set("app_id",  OtherConstant.appId)
//				    
//				    .set("project_id", "")
//				    .set("pf", "4")
//				    .set("pro_manager_no", "")
//				    .set("use_level", "")
//				    .set("task_handle_item", "")
//				    .set("apprate", StrKit.isBlank(pr.getApprate())?4:pr.getApprate())
//				    .set("mould_ap", StrKit.isBlank(pr.getMouldAp())?"":pr.getMouldAp())
//				    .set("apprate_detail", StrKit.isBlank(pr.getApprateDetail())?"":pr.getApprateDetail())
//				    
//				    .set("record_code", pr.getRecordCode())
//				    .set("create_time",new Date())
//				    .set("status", 2)//上报状态 1 成功 2失败
//			);
			
			//上报好差评系统
			Map<String, Object> map = new HashMap<>();//BeanKit.objectToMap(pr);
			map.put("areaId", OtherConstant.areaId);
			map.put("areaName", OtherConstant.areaName);
			map.put("taskId", pr.getTaskId());
			map.put("taskType", StrKit.isBlank(pr.getTaskType())?"1":pr.getTaskType());//及办件 =1 ，承诺办件=2  默认传1
			map.put("taskName", pr.getTaskName());
			map.put("subMatter", StrKit.isBlank(pr.getSubMatter())?"1":pr.getSubMatter());//申请人类型（1 自然人  2 企业法人 3事业法人  4 社会组织法人  5非法人企业  6 行政机关    9其他组织）  (传默认值 1 )

			map.put("proStatus", StrKit.isBlank(pr.getProStatus())?"3":pr.getProStatus());//1 受理  ， 2 已受理  3已办结 取号为受理 呼叫为已受理 评价为办结是否可以这么理解(可以)
			map.put("proDepartId", OtherConstant.proDepartId);//受理部门编码
			map.put("proDepart", OtherConstant.proDepart);//受理部门
			map.put("deptCode", OtherConstant.deptCode);//受理部门信用代码
			map.put("proManager", pr.getProManager()); //经办人姓名  窗口人员姓名
		
			map.put("userName", pr.getUserName());
			map.put("userCert", pr.getUserCert());
			map.put("phonumber", StrKit.isBlank(pr.getPhonumber())?"":pr.getPhonumber());//暂时不传 电话号码
			map.put("certType", StrKit.isBlank(pr.getCertType())?"111":pr.getCertType());//身份证：111
			map.put("appId", OtherConstant.appId);
			
			map.put("projectId", "");
			map.put("pf", "4");//pr.getPf()
			map.put("proManagerNo", "");
			map.put("useLevel", "");
			map.put("taskHandleItem", "");
			map.put("apprate", StrKit.isBlank(pr.getApprate())?4:pr.getApprate());// 评价级别  默认不评价：4   （5：非常满意 4满意，3基本满意，2不满意，1非常不满意）
			map.put("mouldAp", StrKit.isBlank(pr.getMouldAp())?"":pr.getMouldAp());//评价选项
			map.put("apprateDetail", StrKit.isBlank(pr.getApprateDetail())?"":pr.getApprateDetail());
			
			String result = HttpUtil.get(OtherConstant.ReportingUrl, map);
			log.info("上报好差评系统结果："+result);
			HttpRequestVo r = JSONObject.parseObject(result, HttpRequestVo.class);
			
			Document doc = Jsoup.parse(result);
			Elements el = doc.getElementsByClass("expression");
			Elements els = el.select("li");
			for(Element elementLi : els) {
				//elementLi.
			}
			
			
			 HtmlPage page=WebClient.getPage( "http://blog.java1234.com/index.html" ); // 解析获取页面
             HtmlForm form=page.getFormByName( "myform" );  // 得到搜索Form
             //HtmlTextInput textField=form.getInputByName( "q" );  // 获取查询文本框
             HtmlSubmitInput button=form. getInputByName ( "submitButton" );  // 获取提交按钮
             //textField.setValueAttribute( "java" );  // 文本框“填入”数据
             HtmlPage page2=button.click();  // 模拟点击 
             
			
			//doc.getElementById(id)
//			if("200".equals(r.getState())) {
//				Db.use().update(
//					    Entity.create("pdj_record")
//					    .set("id", lid.intValue()),
//					    Entity.create("pdj_record")
//					    .set("status", 1)//上报状态 1 成功 2失败
//				);
//				return RetKit.ok("上报成功！");
//			}else {
//				return RetKit.fail("上报失败！"+r.getError());
//			}
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

	/**
	 * 	预约
	 */
	public RetKit appoint(String openid, String userName, String idcard, String phone, String taskId, String taskName,
			String createTime, Integer status, String appId, String areaId, String areaName,String orderDate) {
		if(StrKit.isBlank(openid) || StrKit.isBlank(idcard) || StrKit.isBlank(userName) || StrKit.isBlank(phone)|| StrKit.isBlank(orderDate)) {
			return RetKit.fail("参数不正确");
		}
		try {
			
			List<Entity> result = Db.use().query("SELECT * FROM `pdj_appoint` WHERE user_name=?	AND idcard=? AND phone=? AND "
					+ " DATE(order_date) >= ?",userName,idcard,phone,DateUtil.format(DateUtil.parseDate(orderDate), "yyyy-MM-dd"));
			
			if(result != null && result.size()>2) {
				return RetKit.fail("同一日期最多预约三次！");
			}
			
			String dateTimeStr = Long.toString(System.currentTimeMillis(), 100);
			String adc = "";
			for(int i =0 ;i<3;i++) {
				adc += "" + (char)(Math.random()*26+'a');
			}
			String appointCode = dateTimeStr + adc;
			Date _createTime = new Date();//预约订单创建时间
			
			//获取排队机当前某业务排号
			String orderNumber = taskId + "-"+1;
			List<PdjAppoint> appoints = Db.use().find(Entity.create("pdj_appoint").set("task_id", taskId).set("order_date", DateUtil.format(DateUtil.parseDate(orderDate), "yyyy-MM-dd")).set("area_id", OtherConstant.appId),PdjAppoint.class);
			if(appoints.size()>0) {
				PdjAppoint lastPoint = appoints.get(appoints.size()-1);
				String lastOrderNumber = lastPoint.getOrderNumber();
				if(StrKit.notBlank(lastOrderNumber)) {
					String[] s = lastOrderNumber.split("-");
					if(s.length>1) {
						Integer num = Integer.valueOf(s[1])+1;
						orderNumber = s[0]+num;
					}
				}
			}
			// 生成二维码
			Map<String, Object> map = new HashMap<>();
			map.put("openid",openid);
			map.put("userName", userName);
			map.put("appointCode", appointCode);
			map.put("idcard", idcard);
			map.put("phone", phone);
			map.put("taskId", taskId);
			map.put("taskName", taskName);
			map.put("createTime", MDateUtil.dateToString(_createTime, null));
			map.put("status", 0);
			map.put("appId", StrKit.isBlank(appId)?OtherConstant.appId:appId);
			map.put("areaId", StrKit.isBlank(areaId)?OtherConstant.areaId:areaId);
			map.put("areaName", StrKit.isBlank(areaName)?OtherConstant.areaName:areaName);
			map.put("orderNumber", orderNumber);
			map.put("orderDate", orderDate);//预约票的时间
			
			String codeName = System.currentTimeMillis() + adc +"_unicom_code.jpg";
			String codePath = uploadPath + codeName;
			QrCodeUtil.generate(JSONObject.toJSONString(map), // 二维码内容
					QrConfig.create().setErrorCorrection(ErrorCorrectionLevel.H).setImg(uploadPath + "syspic/pdj.png"), // 附带logo  
					FileUtil.file(codePath)// 写出到的文件
			);
			
			Long ids = Db.use().insertForGeneratedKey(
				    Entity.create("pdj_appoint")
				    .set("openid", openid)
				    .set("user_name", userName)
				    .set("appoint_code", appointCode)
				    .set("idcard", idcard)
				    .set("phone", phone)
				    .set("task_id", taskId)
				    .set("task_name", taskName)
				    .set("create_time", _createTime)
				    .set("status", 0)
				    .set("app_id", StrKit.isBlank(appId)?OtherConstant.appId:appId)
				    .set("area_id", StrKit.isBlank(areaId)?OtherConstant.areaId:areaId)
				    .set("area_name", StrKit.isBlank(areaName)?OtherConstant.areaName:areaName)
				    .set("code_path", codeName)
				    .set("order_number", orderNumber)
				    .set("order_date", orderDate)
			);
			return RetKit.ok("预约成功！预约码:"+appointCode);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("预约失败！错误信息："+e.getMessage());
			return RetKit.fail("预约失败！"+e.getMessage());
		}
	}
	
	/**
	 * 预约列表
	 * @return
	 */
	public RetKit appointList(String openid,String taskId) {
		try {
			List<PdjAppoint> appoints = new ArrayList<>();
			if(StrKit.notBlank(taskId)) {
				List<String> inids = JSONObject.parseArray(taskId, String.class);
				String ids = "";
				for (String string : inids) {
					ids += ","+string;
				}
				if(StrKit.notBlank(ids)) {
					appoints = Db.use().findAll(Entity.create("pdj_appoint").set("task_id", ids).set("openid", openid),PdjAppoint.class);
//					List<Entity> entitys = Db.use().query("SELECT * FROM `pdj_appoint` WHERE task_id  in (?)  AND  openid = ?;",ids.substring(1),openid);
//					appoints = BeanKit.changeToListBean(entitys, PdjAppoint.class);
				}else {
					appoints = Db.use().findAll(Entity.create("pdj_appoint").set("openid", openid),PdjAppoint.class);
				}
			}else {
				appoints = Db.use().findAll(Entity.create("pdj_appoint").set("openid", openid),PdjAppoint.class);
			}
			
			return RetKit.okData(appoints);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("获取失败！"+e.getMessage());
			return RetKit.fail("获取失败！");
		}
	}

	public RetKit getTaskTypes() {
		try {
			List<PdjTask> tasks = Db.use().find(Entity.create("pdj_task"),PdjTask.class);
			return RetKit.okData(tasks);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("获取失败！"+e.getMessage());
			return RetKit.fail("获取失败！");
		}
	}

	public RetKit upAppoint(String appointCode) {
		 try {
			Db.use().update(
				    Entity.create("pdj_appoint")
				    .set("appoint_code", appointCode),
				    Entity.create("pdj_appoint")
				    .set("appoint_code", appointCode)
			);
			return RetKit.ok();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RetKit.fail(e.getMessage());
		}
	}

	
	
}
