package com.cqsd.config;

import com.cqsd.net.interceptor.LoginInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties(prefix = "my.login.interceptor")
public class LoginConfig implements WebMvcConfigurer {
	private final LoginInterceptor loginInterceptor;
	private String[] patterns;
	private String[] excludePatterns;
	
	/**
	 * 设置登陆拦截需要拦截的url
	 * @param patterns url数组
	 */
	public void setPatterns(String[] patterns) {
		this.patterns = patterns;
	}
	
	/**
	 * 设置需要排除的url
	 * @param excludePatterns url数组
	 */
	public void setExcludePatterns(String[] excludePatterns) {
		this.excludePatterns = excludePatterns;
	}
	
	public LoginConfig(LoginInterceptor loginInterceptor) {
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
				.excludePathPatterns(excludePatterns);
		
	}
}
