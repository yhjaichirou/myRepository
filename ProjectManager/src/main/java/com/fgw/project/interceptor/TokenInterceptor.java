package com.fgw.project.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Enumeration<String> ems = request.getHeaderNames();
		String token = request.getHeader("X-Token");
		// verify token
		RetKit isOK = TokenUtils.verify(token);
 
		if (((int)isOK.get("code")) == 200) {
			return true;
		} else {
			response.setStatus(403);
			logger.error((String)isOK.get("msg"));
			return false;
		}
 
	}
}
