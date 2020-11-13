package com.yhj.singlesign.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yhj.singlesign.interceptors.UserLoginHandlerInterceptor;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
	@Resource
	UserLoginHandlerInterceptor userLoginInterceptor;

	

	
    /**
     * 添加自定义拦截器
     */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userLoginInterceptor)
           .addPathPatterns("/**")//拦截的访问路径，拦截所有
           .excludePathPatterns("/user/**","/login/**","/js/**","/css/**","/images/**","/common/**");//排除的请求路径，排除静态资源路径
		
//		registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/error/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	private Integer timeDay = 5;
	
	private TimeUnit timeUnit = TimeUnit.DAYS;

    /**
     * 添加静态资源映射路径，css、js等都放在classpath下的static中
     */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		
//		registry.addResourceHandler("/static/upload/**").addResourceLocations("file:" + upload_dir );
//		WebMvcConfigurer.super.addResourceHandlers(registry);
		
//		registry
//      .addResourceHandler("/image/**/*")
//      .addResourceLocations("classpath:/static/image/");
//		
//		registry
//        .addResourceHandler("/css/**/*.css")
//        .addResourceLocations("classpath:/static/css/");
////        .setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
////		
//		registry.addResourceHandler("/img/**/*.jpg","/img/**/*.png","/img/**/*.jpeg","/img/**/*.gif")
//		.addResourceLocations("classpath:/static/image/");
////		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
////		
//		registry.addResourceHandler("/js/**/*.js")
//		.addResourceLocations("classpath:/static/js/");
////		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
////		
//		registry.addResourceHandler("/common/**/*")
//		.addResourceLocations("classpath:/static/common/");
////		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
////		
//		registry.addResourceHandler("/ueditor/**/*")
//		.addResourceLocations("classpath:/static/ueditor/");
////		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
////		
//		registry.addResourceHandler("/layuiadmin/**/*.js")
//		.addResourceLocations("classpath:/static/layuiadmin/");
////		.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
		
		
		registry
	      .addResourceHandler("/image/**/*.jpg","/image/**/*.png","/image/*.jpg","/image/*.png")
	      .addResourceLocations("classpath:/static/image/");
			
			registry
	        .addResourceHandler("/css/**/*.css")
	        .addResourceLocations("classpath:/static/css/")
	        .setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
			
			registry.addResourceHandler("/img/**/*.jpg","/img/**/*.png","/img/**/*.jpeg","/img/**/*.gif")
			.addResourceLocations("classpath:/static/image/")
			.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
			
			registry.addResourceHandler("/js/**/*.js")
			.addResourceLocations("classpath:/static/js/")
			.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
			
			registry.addResourceHandler("/common/**/*")
			.addResourceLocations("classpath:/static/common/")
			.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
			
			registry.addResourceHandler("/ueditor/**/*")
			.addResourceLocations("classpath:/static/ueditor/")
			.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
			
			registry.addResourceHandler("/layuiadmin/**/*.js")
			.addResourceLocations("classpath:/static/layuiadmin/")
			.setCacheControl(CacheControl.maxAge(timeDay, timeUnit).cachePublic());
	}
	


}
