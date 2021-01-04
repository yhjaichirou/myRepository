package com.fgw.project.controller.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fgw.project.model.po.Config;
import com.fgw.project.repository.IConfigRepository;
import com.fgw.project.service.DepartService;
import com.fgw.project.service.YjService;
import com.fgw.project.util.BeanKit;
import com.fgw.project.util.MDateUtil;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;

/**
 * 后台接口
 * @author yhj
 * @date 2020年12月17日
 */
@RestController
@RequestMapping("/config")
@CrossOrigin
public class ConfigController {

	@Autowired
	private IConfigRepository cR;
	
	@RequestMapping("/getConfig")
	public RetKit getPorjects() {
		Config c = cR.findById(1).get();
		try {
			Map<String, Object> m = BeanKit.objectToMap(c);
			String types = c.getYjType();
			if(StrKit.notBlank(types)) {
				JSONArray ar = JSONObject.parseArray(types);
				m.put("yjType", ar);		
			}
			return RetKit.okData(m);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
		
	}
	
	@RequestMapping("/updateConfig")
	public RetKit updateConfig(@RequestBody String param) {
		JSONObject ob = JSONObject.parseObject(param);
		Config c = new Config();
		c.setYjDay(ob.getInteger("yjDay"));
		c.setYjTime(MDateUtil.stringToDate(ob.getString("yjTime"), MDateUtil.formatDate));
		c.setYjType(ob.getString("yjType"));
		c.setDwMaxPel(ob.getInteger("dwMaxPel"));
		c.setMesDefaultPel(ob.getString("mesDefaultPel"));
		c.setMesMessage(ob.getInteger("mesMessage"));
		cR.save(c);
		return RetKit.okData(c.getId());
	}
}
