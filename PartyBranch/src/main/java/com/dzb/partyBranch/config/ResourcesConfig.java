package com.dzb.partyBranch.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dzb.partyBranch.interceptor.ApiInterceptor;
import com.dzb.partyBranch.interceptor.ErrorInterceptor;


@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	@Resource(name = "errorInterceptor")
	ErrorInterceptor errorInterceptor;
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(errorInterceptor);
		
//		registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/error/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	private Integer timeDay = 5;
	
	private TimeUnit timeUnit = TimeUnit.DAYS;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/upload/**").addResourceLocations("file:" + upload_dir );
//		WebMvcConfigurer.super.addResourceHandlers(registry);
		
//		registry
//        .addResourceHandler("/css/**/*.css")
//        .addResourceLocations("classpath:/static/css/")
//        .setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
//		
//		registry.addResourceHandler("/img/**/*.jpg","/img/**/*.png","/img/**/*.jpeg","/img/**/*.gif")
//		.addResourceLocations("classpath:/static/image/")
//		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
//		
//		registry.addResourceHandler("/js/**/*.js")
//		.addResourceLocations("classpath:/static/js/")
//		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
//		
//		registry.addResourceHandler("/common/**/*")
//		.addResourceLocations("classpath:/static/common/")
//		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
//		
//		registry.addResourceHandler("/ueditor/**/*")
//		.addResourceLocations("classpath:/static/ueditor/")
//		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
//		
//		registry.addResourceHandler("/layuiadmin/**/*.js")
//		.addResourceLocations("classpath:/static/layuiadmin/")
//		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
	}
	


}
