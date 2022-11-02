package com.cqsd.config;

import com.cqsd.net.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMVConfig implements WebMvcConfigurer {
	private final LoginInterceptor loginInterceptor;
	@Value("${my.login.interceptor.patterns}")
	private String[] patterns;
	@Value("${my.login.interceptor.excludepatterns}")
	
	private String[] excludePathPatterns;
	
	public WebMVConfig(LoginInterceptor loginInterceptor) {
		this.loginInterceptor = loginInterceptor;
	}
	
	/**
	 * Add Spring MVC lifecycle interceptors for pre- and post-processing of
	 * controller method invocations and resource handler requests.
	 * Interceptors can be registered to apply to all requests or be limited
	 * to a subset of URL patterns.
	 *
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
				.addInterceptor(loginInterceptor)
				.addPathPatterns(patterns)
				.excludePathPatterns(excludePathPatterns);
		
	}
}
