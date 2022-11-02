package com.cqsd.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper;
import com.cqsd.net.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {
	private final Environment environment;
	private static String employeeDefaltPassword;
	
	public AppConfig(Environment environment) {
		this.environment = environment;
	}
	
	@Bean(initMethod = "init")
	public DruidDataSourceWrapper dataSource() {
		final var wrapper = new DruidDataSourceWrapper();
		wrapper.setDriverClassName(environment.getProperty("db.driverClassName"));
		wrapper.setUrl(environment.getProperty("db.url"));
		wrapper.setUsername(environment.getProperty("db.username"));
		wrapper.setPassword(environment.getProperty("db.password"));
		return wrapper;
	}
	
	/**
	 * User Login Interceptor
	 *
	 * @return bean
	 */
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	public String getEmployeeDefaltPassword() {
		return employeeDefaltPassword;
	}
	
	/**
	 * set defaltPassword
	 *
	 * @param employeeDefaltPassword defaltPassword
	 */
	@Value("${defalt.config.password}")
	
	public void setEmployeeDefaltPassword(String employeeDefaltPassword) {
		AppConfig.employeeDefaltPassword = employeeDefaltPassword;
	}
}
