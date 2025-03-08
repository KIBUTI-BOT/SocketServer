package com.kibuti.socketserver.WebSocket.Config;


import com.kibuti.socketserver.WebSocket.Service.UserTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

/**
 * Listens for WebSocket connection and disconnection events
 */
@Component
public class WebSocketEventListener {

    @Autowired
    private UserTrackingService userTrackingService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Handles user connection events
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headers.getSessionId();

        // In a real app, you would get the user ID from authentication
        // Here we use a header as a simple example
        String userIdStr = headers.getFirstNativeHeader("userId");
        if (userIdStr != null) {
            UUID userId = UUID.fromString(userIdStr);
            userTrackingService.userConnected(userId, sessionId);

            // Notify others that this user is online
            messagingTemplate.convertAndSend("/topic/users.online", userId);
        }
    }

    /**
     * Handles user disconnection events
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headers.getSessionId();

        // Find which user this session belongs to and mark them as disconnected
        // In a real app, you would have a better way to map sessions to users
        userTrackingService.getConnectedUsers().entrySet().stream()
                .filter(entry -> entry.getValue().equals(sessionId))
                .findFirst()
                .ifPresent(entry -> {
                    UUID userId = entry.getKey();
                    userTrackingService.userDisconnected(userId);

                    // Notify others that this user is offline
                    messagingTemplate.convertAndSend("/topic/users.offline", userId);
                });
    }
}