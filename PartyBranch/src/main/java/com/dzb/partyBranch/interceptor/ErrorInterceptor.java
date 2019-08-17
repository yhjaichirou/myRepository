package com.dzb.partyBranch.interceptor;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class ErrorInterceptor implements HandlerInterceptor {
	
	private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String patt = "^(\\/dzb\\/admin/.*)";
//		Pattern r = Pattern.compile(patt);
//		java.util.regex.Matcher m = r.matcher(request.getRequestURI());
//		if(m.find()) {
//			
//		}
//		int status = response.getStatus();
//		String url = request.getContextPath();
//		if (errorCodeList.contains(response.getStatus())) {
//			response.sendRedirect(url+"/error/" + response.getStatus());
//			return false;
//		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			if (response.getStatus() == 500) {
				modelAndView.setViewName("/error/500");
			} else if (response.getStatus() == 404) {
				modelAndView.setViewName("/error/404");
			}
		}
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
