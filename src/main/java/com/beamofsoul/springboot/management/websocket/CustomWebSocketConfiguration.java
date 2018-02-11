package com.beamofsoul.springboot.management.websocket;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @ClassName CustomWebSocketConfiguration
 * @Description 自定义websocket配置类(在用户消息中使用了websocket功能)
 * @author MingshuJian
 * @Date 2017年3月21日 下午3:17:47
 * @version 1.0.0
 */
@Configuration
@EnableWebSocket
public class CustomWebSocketConfiguration implements WebSocketConfigurer {

	@Resource
	private WebSocketHandler customWebSocketHandler;
	
	@Resource
	private WebSocketHandler customSystemLogWebSocketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//消息管理WebSocket
		registry.addHandler(customWebSocketHandler, "/sockjs").withSockJS();
		//系统日志WebSocket
		registry.addHandler(customSystemLogWebSocketHandler, "/systemLog").withSockJS();
	}

}
