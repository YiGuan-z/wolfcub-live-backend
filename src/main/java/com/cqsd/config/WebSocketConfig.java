package com.cqsd.config;

import com.cqsd.socket.core.netty.WebSocketNettyServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {
	/**
	 * Set buttle server port
	 * @return
	 */
	@Bean
	public WebSocketNettyServer webSocketNettyServer(){
		final var server = new WebSocketNettyServer();
		server.setServerPort(9999);
		return server;
	}
}
