package com.fgw.project.controller.msg;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.User;
import com.fgw.project.model.vo.WxUserInfo;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IUserRepository;
import com.fgw.project.service.ApiService;
import com.fgw.project.service.TaskService;
import com.fgw.project.service.UserService;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;
import com.fgw.project.util.WxResult;

import cn.hutool.http.HttpUtil;

@RestController
@RequestMapping("/msg")
@CrossOrigin
public class MsgController {

	@Resource
	private MsgApiService msgApi;
	
	/**
	 * 查询是否认证
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/callback", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public RetKit callback() {
		try {
			//增加短信记录
			
			
			
			return RetKit.ok("发送成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail("发送失败！"+e.getMessage());
		}
	}
	
	
}
