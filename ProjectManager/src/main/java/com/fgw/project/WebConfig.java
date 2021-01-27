package com.fgw.project;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fgw.project.interceptor.TokenInterceptor;

/**
 * 
 * @author yhj
 * @date 2020年12月18日
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Resource(name = "tokenInterceptor")
	TokenInterceptor tokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(tokenInterceptor)
		.excludePathPatterns("/wx/**")
		.excludePathPatterns("/project/admin/login")
		.excludePathPatterns("/project/error");// 排除的请求路径，排除静态资源路径
//      .addPathPatterns("/api/**")//拦截的访问路径，拦截所有
//      .excludePathPatterns("/static/*");//排除的请求路径，排除静态资源路径

//	registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/error/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping("/**")
				// 设置允许跨域请求的域名
				.allowedOrigins("*").allowedHeaders("*")
//	                .allowCredentials(true)//是否允许证书 不再默认开启
				// 设置允许的方法
				.allowedMethods("GET", "POST", "PUT", "DELETE");
//	                .maxAge(3600);//跨域允许时间
	}
}
