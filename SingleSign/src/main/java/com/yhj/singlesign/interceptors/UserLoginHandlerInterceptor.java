package com.yhj.singlesign.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yhj.singlesign.service.UserService;
import com.yhj.singlesign.utils.CookieUtil;
import com.yhj.singlesign.utils.StrKit;
import com.yhj.singlesign.vo.User;

@Component("userLoginInterceptor")
public class UserLoginHandlerInterceptor implements HandlerInterceptor {

    public static final String COOKIE_NAME = "USER_TOKEN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = CookieUtil.getCookieValue(request, COOKIE_NAME);
        User user = userService.getUserByToken(token);
        if (StrKit.isBlank(token) || null == user) {
            // 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect("http://localhost:8081/single/login?redirect=" + request.getRequestURL());
            // 返回false
            return false;
        }
        // 把用户信息放入Request
        request.setAttribute("user", user);
        // 返回值决定handler是否执行。true：执行，false：不执行。
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
    }
}