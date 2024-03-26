package com.example.demo.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.intercepter.LoginIntercepter;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new LoginIntercepter())
	                .addPathPatterns("/**")
	                .excludePathPatterns("/", "/login", "/selfIntroduction");
	}
	
}