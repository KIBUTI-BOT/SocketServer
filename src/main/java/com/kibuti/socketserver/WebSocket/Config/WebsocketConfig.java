package com.kibuti.socketserver.WebSocket.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
  //Todo: Implement the WebSocketMessageBrokerConfigurer methods
    // 1. registerStompEndpoints(StompEndpointRegistry registry)
    // 2. configureMessageBroker(MessageBrokerRegistry registry)

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //This will be used to create a WebSocket connection, for example, ws://localhost:8080/gs-guide-websocket, and it can be anything eg /ws
        registry.addEndpoint("/gs-guide-websocket")
                 .setAllowedOriginPatterns("*")
                .withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
       registry.enableSimpleBroker("/topic");
       registry.setApplicationDestinationPrefixes("/app");
    }
}
