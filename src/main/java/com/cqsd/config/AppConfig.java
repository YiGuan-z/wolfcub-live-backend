package com.cqsd.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:db.properties")
public class AppConfig {
	private final Environment environment;
	
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
	
}
