package com.kibuti.socketserver.WebSocket.Service;


import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple service to track connected users
 */
@Service
public class UserTrackingService {

    // Map of userId to sessionId - shows which users are online
    private final Map<UUID, String> connectedUsers = new ConcurrentHashMap<>();

    /**
     * Register a user as connected
     */
    public void userConnected(UUID userId, String sessionId) {
        connectedUsers.put(userId, sessionId);
    }

    /**
     * Mark a user as disconnected
     */
    public void userDisconnected(UUID userId) {
        connectedUsers.remove(userId);
    }

    /**
     * Check if a user is connected
     */
    public boolean isUserConnected(UUID userId) {
        return connectedUsers.containsKey(userId);
    }

    /**
     * Get the current list of connected user IDs
     */
    public Map<UUID, String> getConnectedUsers() {
        return connectedUsers;
    }
}