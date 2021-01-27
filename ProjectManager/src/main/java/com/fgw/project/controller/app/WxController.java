package com.fgw.project.controller.app;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.fgw.project.service.UserService;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.StrKit;
import com.fgw.project.util.WxResult;

import cn.hutool.http.HttpUtil;

@RestController
@RequestMapping("/wx")
@CrossOrigin
public class WxController {

	@Value("${appid}")
	private String appid;
	@Value("${secret}")
	private String secret;
	
	@Resource
	private UserService userService;
	@Resource
	private ApiService apiService;
	@Autowired
	private IUserRepository userR;
	@Autowired
	private IPeopleRepository peoR;
	
	/**
	 * 查询是否认证
	 * 
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/isAuth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public RetKit isAuth(String openid) {
		try {
			Map<String,Object> u = peoR.getByStatusAndOpenid(1,openid);
			if(u.isEmpty()) {
				return RetKit.fail("未认证！");
			}else {
				return RetKit.okData(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param openid
	 * @return
	 */
	@RequestMapping(value = "/authPromis", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public RetKit authPromis(String openid,String phone,String nickName,String avatarUrl,Integer gender) {
		try {
			if(StrKit.isBlank(openid) || StrKit.isBlank(phone)) {
				return RetKit.fail("参数错误！");
			}
			People p = peoR.findByMobile(phone);
			if(p==null) {
				return RetKit.fail("非项目人员，无法参与，请联系相关部门管理员！");
			}
			p.setOpenid(openid);
			p.setNickName(nickName);
			p.setAvatarUrl(avatarUrl);
			p.setGender(gender);
			peoR.save(p);
			Map<String,Object> u = peoR.getByStatusAndOpenidAndMobile(1,openid,phone);
			if(u.isEmpty()) {
				return RetKit.fail("认证失败！");
			}else {
				return RetKit.okData(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}

	/**
	 * -------------------------微信在使用的 接口--------------------------
	 */
	//@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@RequestMapping("/auth")
	@ResponseBody
	public RetKit getAuthentication(String code) {
		try {
			if (StrKit.isBlank(code)) {
				return RetKit.fail("认证码不能为空！");
			}
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("appid", appid);
			paramMap.put("secret", secret);
			paramMap.put("js_code", code);
			paramMap.put("grant_type", "authorization_code");
			String result3 = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", paramMap);
			WxResult appuser = JSONObject.parseObject(result3, WxResult.class);
			if (appuser.getErrcode() != null) {
				return RetKit.fail(appuser.getErrmsg());
			}
			return RetKit.okData(appuser);
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
	}
	
	/**
	 * -------------------------微信在使用的 接口--------------------------
	 */
//	//@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//	@RequestMapping("/getAccessToken")
//	@ResponseBody
//	public RetKit getAccessToken(String code) {
//		try {
//			if (StrKit.isBlank(code)) {
//				return RetKit.fail("认证码不能为空！");
//			}
//			Map<String, Object> paramMap = new HashMap<>();
//			paramMap.put("appid", appid);
//			paramMap.put("secret", secret);
//			paramMap.put("js_code", code);
//			paramMap.put("grant_type", "authorization_code");
//			String result3 = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", paramMap);
//			WxResult appuser = JSONObject.parseObject(result3, WxResult.class);
//			if (appuser.getErrcode() != null) {
//				return RetKit.fail(appuser.getErrmsg());
//			}
//			return RetKit.okData(appuser);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return RetKit.fail(e.getMessage());
//		}
//	}
	

	@RequestMapping("updateUserInfo")
	@ResponseBody
	public RetKit getAuthentication(String userinfo, String openid) {
		WxUserInfo _userinfo = JSONObject.parseObject(userinfo, WxUserInfo.class);
		
		return apiService.addWxUserInfo(_userinfo, openid);
	}

	

	@RequestMapping("upload")
	@ResponseBody
	public RetKit upload(MultipartFile img) throws Exception {
		if (!img.getOriginalFilename().equals("") && img.getSize() > 0) {
			try {
//				return RetKit.okData(getphotoPath + UploadFile.upload(photoPath, img));
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return RetKit.fail("上传失败！");
			}
		}
		return RetKit.fail("上传内容不能为空！");
	}
}
