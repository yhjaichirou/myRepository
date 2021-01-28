package com.fgw.project.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fgw.project.constant.URLConstant;
import com.fgw.project.model.po.People;
import com.fgw.project.model.po.Yj;
import com.fgw.project.model.vo.WxUserInfo;
import com.fgw.project.repository.IPeopleRepository;
import com.fgw.project.repository.IYjRepository;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;
import com.fgw.project.util.WxResult;

import cn.hutool.http.HttpUtil;

@Service
public class ApiService {

	@Value("${appid}")
	private String appid;
	@Value("${secret}")
	private String secret;
	@Autowired
	private IPeopleRepository peoR;
	@Autowired
	private IYjRepository yR;

//	@Autowired
//	private CacheManager cacheManager;

	/**
	 * ---------------------------------微信服务接口---------------------------------
	 */
	public RetKit getAccessToken() {
		try {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("appid", appid);
			paramMap.put("grant_type", "client_credential");
			paramMap.put("secret", secret);
			String result3 = HttpUtil.get(URLConstant.URL_GET_TOKKEN, paramMap);
			WxResult appuser = JSONObject.parseObject(result3, WxResult.class);
//			Cache tokenCache = cacheManager.getCache("accessToken");
//			tokenCache.put(openid+"_token", appuser.getAccessToken());

			if (appuser.getErrcode() != null && appuser.getErrcode() != 0) {
				return RetKit.fail(appuser.getErrmsg());
			}
			return RetKit.okData(appuser.getAccess_token());
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail("网络错误！");
		}
	}
	
	public RetKit addWxUserInfo(WxUserInfo userinfo, String openid) {
		try {
			People p = peoR.findByOpenid(openid);
			p.setNickName(userinfo.getNickName());
			p.setAvatarUrl(userinfo.getAvatarUrl());
			p.setGender(userinfo.getGender());
			peoR.save(p);
			return RetKit.ok("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail("网络错误！");
		}
	}

	
	/**
	 * ---------------------------------业务---------------------------------
	 */
	
	public RetKit getNoticeList(Integer orgId, Integer status,Integer peopleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RetKit getYjList(Integer orgId, Integer status,Integer peopleId) {
		List<Yj> ys = yR.findAllByOrgId(orgId);
		if(status == null) {
			ys = yR.findAllByOrgId(orgId);
		}else {
			ys = yR.findAllByOrgIdAndStatus(orgId,status);
		}
		ys = ys.stream().filter((Yj y)->(StrKit.isBlank(y.getNoticePeople())?false:Arrays.asList(y.getNoticePeople().split(",")).contains(peopleId.toString()))).collect(Collectors.toList());
		return RetKit.okData(ys);
	}

	
	
	
	
	
	
//
//
//	/**
//	 * ---------------------------------业务---------------------------------
//	 */
//
//	public RetKit getUserDetail(String openid) {
//		try {
////			List<UserInfo> us = Db.use().find(Entity.create("handle_userinfo").set("openid", openid),UserInfo.class);
//			List<WxUserInfo> us = Db.use().query("SELECT info.*,r.room_name as room_name,r.room_type as roomType,r.room_status as roomStatus,r.room_code as roomCode,r.room_create_time as roomCreateTime "
//					+ " FROM kefu_customer info "
//					+ " left join kefu_room r on r.id = info.room_id "
//					+ " WHERE info.openid=?", WxUserInfo.class, openid);
//			
//			return us.size()>0 ?RetKit.okData(us.get(0)): RetKit.fail("用户不存在！");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}
//	
//	public RetKit getRoomList() {
//		try {
//			List<Room> rooms = Db.use().findAll(Entity.create("kefu_room"),Room.class);
//			rooms = rooms.stream().map((Room r )->{
//				r.setRoomImg(getphoto+r.getRoomImg());
//				return r;
//			}).collect(Collectors.toList());
//			return RetKit.okData(rooms);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}
//
//	public RetKit getKefuList() {
//		try {
//			List<WxUserInfo> us = Db.use().query("SELECT info.*,r.room_name as room_name,r.room_type as roomType,r.room_status as roomStatus,r.room_code as roomCode,r.room_create_time as roomCreateTime "
//					+ " FROM kefu_customer info "
//					+ " left join kefu_room r on r.id = info.room_id "
//					+ " WHERE info.room_id is not null ", WxUserInfo.class);
//			return us.size()>0 ?RetKit.okData(us): RetKit.fail("客服房间暂停服务！");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}
//
//	public RetKit isBusy(Integer roomCode,Integer roomId,String openid,Integer status) {
//		try {
//			List<Room> rooms = Db.use().findAll(Entity.create("kefu_room").set("room_code", roomCode),Room.class);
//			if(rooms.size()>0) {
//				Db.use().update(Entity.create("kefu_room").set("room_status", status), Entity.create("kefu_room").set("room_code", roomCode));
//				return RetKit.ok();
//			}else {
//				return RetKit.fail("该房间不存在！");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return RetKit.fail("网络错误！");
//		}
//	}

}
