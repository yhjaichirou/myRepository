package com.dzb.partyBranch.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dzb.partyBranch.constant.LoginConstant;
import com.dzb.partyBranch.kit.IpUtil;
import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.model.vo.UserAuthentic;
import com.dzb.partyBranch.model.po.User;
import com.dzb.partyBranch.repository.IUserRepository;
import com.dzb.partyBranch.service.ILoginService;


/**
 * 登陆
 * 
 * @author yhj
 * @date 2018年10月24日
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("admin")
public class LoginController {

	@Resource
	private ILoginService loginService;

	@Resource
	private IUserRepository iUserRepository;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user = (String)obj;
//		UserAuthentic user = (UserAuthentic) obj;
		if (!user.equals("anonymousUser")){
			return "forward:./index/index";
		}
		return "/views/user/login";
//		return url_login;
	}

	@RequestMapping("/")
	public String defaultHome() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user = (String)obj;
//		UserAuthentic user = (UserAuthentic) obj;
		if (!user.equals("anonymousUser")){//怎么取消默认的anonymousUser值
			return "forward:index";
		}
		return "forward:login";
	}

	@RequestMapping("/fail")
	public String error() {
		return "/error/loginfail";
	}
	
	@Resource(name = "myAuthenticationManager")
	private AuthenticationManager authenticationManager;

	@RequestMapping(value = "/loginIn", method = RequestMethod.POST)
	@ResponseBody
	public RetKit loginIn(HttpServletRequest request, HttpServletResponse response) {

		String loginIp = IpUtil.getIpAddr(request);
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("password");
//		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		
		try {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, pwd);
			Authentication authentication = authenticationManager.authenticate(authRequest); // 需要验证的对象authRequest ， 去验证authenticate
			SecurityContextHolder.getContext().setAuthentication(authentication);
			HttpSession session = request.getSession();
			session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
			
			Authentication principal = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(JSON.toJSONString(session));
			if (authentication != null && authentication.isAuthenticated()) {// 判断验证是否成功
				UserAuthentic user = (UserAuthentic) authentication.getPrincipal();
				System.out.println(JSON.toJSONString(user));
				//判断是否具有登陆权限
//				if (loginService.isLoginIn(user.getId(), roleId)) {
//					return RetKit.fail("此用户暂无权限！");
//				}
				Optional<User> opUser = iUserRepository.findById (user.getId());
//				session.setAttribute("roleId", roleId);
				session.setAttribute("userId", user.getId());
				session.setAttribute("user", opUser.get());
				request.authenticate(response);
				request.getSession().setAttribute(LoginConstant.loginUserCacheName, user);
				request.getSession().setAttribute(LoginConstant.ROLE, user.getRoleId());
				loginService.createLoginLog(user.getId(), loginIp);
				
				System.out.println("------------"+request.getSession().getAttribute("ROLE"));
				
				return RetKit.ok().set("returnUrl", "/index");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RetKit.fail(e.getMessage());
		}
		return RetKit.fail("登录失败!");
	}

	@RequestMapping("/validateHasUserName")
	@ResponseBody
	public Boolean validateHasUserName(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		return loginService.hasUserName(userName);
	}

	@RequestMapping("/validatePwd")
	@ResponseBody
	public Boolean validatePwd(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("password");
		return loginService.validatePwdAndName(userName, pwd);
	}

	@RequestMapping("/updataPwd")
	@ResponseBody
	public RetKit updataPwd(HttpServletRequest request) {
		UserAuthentic user = (UserAuthentic) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String params = request.getParameter("params");
		JSONObject m = JSONObject.parseObject(params);
		String oldP = m.getString("oldP");
		String newP = m.getString("newP");
		RetKit retKit = loginService.updataPwd(user.getId(), oldP, newP);
		if (retKit.success()) {
			// 清除session
			Enumeration<String> em = request.getSession().getAttributeNames();
			while (em.hasMoreElements()) {
				request.getSession().removeAttribute(em.nextElement().toString());
			}
			request.getSession().removeAttribute(LoginConstant.loginUserCacheName);
			request.getSession().invalidate();
			return RetKit.ok();
		} else {
			return retKit;
		}

	}
	
	/**
	 * 忘记密码验证
	 */
	@RequestMapping("/seniorValidate")
	public String logout(HttpServletRequest request) {
		return "/views/user/forget";
	}
	
	
	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public RetKit logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getSession());
		
		// 清除session
		Enumeration<String> em = request.getSession().getAttributeNames();
		while (em.hasMoreElements()) {
			request.getSession().removeAttribute(em.nextElement().toString());
		}
		request.getSession().removeAttribute(LoginConstant.loginUserCacheName);
		request.getSession().invalidate();
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				// 如果找到同名cookie，就将value设置为null，将存活时间设置为0，再替换掉原cookie，这样就相当于删除了。
//				if (cookie.getName().equals(LoginConstant.loginUserCacheName)) {
//					cookie.setValue(null);
//					cookie.setMaxAge(0);
//					cookie.setPath(request.getContextPath());
//					response.addCookie(cookie);
//					break;
//				}
//			}
//		}
		return RetKit.ok();
	}
}
