package com.cqsd.config;

import com.cqsd.socket.core.netty.WebSocketNettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "my.netty")
public class WebSocketConfig {
	private Integer port;
	
//	@Value("${my.netty.port}")
	public void setPort(Integer port) {
		this.port = port;
	}
	
	/**
	 * Set buttle server port
	 *
	 * @return
	 */
	@Bean
	public WebSocketNettyServer webSocketNettyServer() {
		final var server = new WebSocketNettyServer();
		server.setServerPort(port);
		return server;
	}
}
