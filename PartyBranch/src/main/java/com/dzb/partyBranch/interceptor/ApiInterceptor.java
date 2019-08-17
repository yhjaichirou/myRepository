package com.dzb.partyBranch.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dzb.partyBranch.kit.RetKit;
import com.dzb.partyBranch.kit.StrKit;

@Component
public class ApiInterceptor implements HandlerInterceptor {
//	@Autowired
//	private Log logger = LogFactory.getLog(ApiInterceptor.class);
	private String accessToken = "";
	private String platform = "";// 接入平台类型 1：android 2:IOS 3：iPad 4:PC

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		accessToken = request.getHeader("accessToken");
//		platform = request.getHeader("platform");
//		if (platform.equals("1") || platform.equals("2")) {
//			response.sendRedirect("/api/error/errorPlatform");
//			return false;
//		}
//		
//		if (accessToken == null||StrKit.isBlank(accessToken)) {
//			response.sendRedirect("/api/error/errorAccessToken");
//			return false;
//		}else {
//			//数据库查询认证
//		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		PrintWriter writer = null;
//		accessToken = request.getHeader("accessToken");
//		platform = request.getHeader("platform");
//		if (platform.equals("1") || platform.equals("2")) {
//			 try {
//	                writer = response.getWriter();
//	                writer.print(RetKit.fail("设备没有权限访问"));
//
//	            } catch (IOException e) {
//	                logger.error("response error",e);
//	            } finally {
//	                if (writer != null)
//	                    writer.close();
//	            }
//		}
//		
//		if (accessToken == null||StrKit.isBlank(accessToken)) {
//            try {
//                writer = response.getWriter();
//                writer.print(RetKit.fail("accessToken不能为空"));
//
//            } catch (IOException e) {
//                logger.error("response error",e);
//            } finally {
//                if (writer != null)
//                    writer.close();
//            }
//		}
//		 
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
