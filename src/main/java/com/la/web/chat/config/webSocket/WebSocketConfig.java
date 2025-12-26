package com.la.web.chat.config.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuracion de web sockets
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {

		config.enableSimpleBroker("/topic"); // topic o canal
		// /topic/messages

		config.setApplicationDestinationPrefixes("/app"); // endpoint de acceso
		// /app/chat
		// server-side: @MessagingMapping("/chat)

	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat")// connection establishment
				.setAllowedOrigins("http://localhost:5173") // cliente permitido
				.withSockJS();
	}
	// /chat endpoint par connection apka establish hoga
}
