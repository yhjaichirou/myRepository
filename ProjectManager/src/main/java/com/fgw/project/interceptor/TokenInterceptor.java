package com.fgw.project.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fgw.project.model.po.User;
import com.fgw.project.repository.IUserRepository;
import com.fgw.project.util.RetKit;
import com.fgw.project.util.TokenUtils;

/**
 * token拦截器
 * @author yhj
 * @date 2020年12月18日
 */
@Component
public class TokenInterceptor implements HandlerInterceptor{

	private Log logger = LogFactory.getLog(TokenInterceptor.class);
	@Autowired
	private IUserRepository userR;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Enumeration<String> ems = request.getHeaderNames();
		if ("OPTIONS".equals(request.getMethod())) {
           return true;
        }
		boolean isGo = true;
		String token = request.getHeader("X-Token");
		String agentId = request.getHeader("AgentId");
		String url  = request.getRequestURI();
		if(url.equals("/project/admin/login") || url.equals("/project/error")) {
			return true;
		}
		
		// verify token
		RetKit isOK = TokenUtils.verify(token);
		if (((int)isOK.get("code")) != 200) {
			isGo = false;
		}
		
		if(!url.equals("/project/admin/userinfo")){
			logger.info("用户详情获取："+request.getHeaderNames());
			User user = userR.findById(Integer.parseInt(agentId));
			if(user!=null && user.getToken().equals(token)) {
				
			}else {
				isGo = false;
			}
		}
		
		if (isGo) {
			return true;
		} else {
			response.setStatus(50008);
			response.sendRedirect("/");
			logger.error((String)isOK.get("msg"));
			return false;
		}
 
	}
}
