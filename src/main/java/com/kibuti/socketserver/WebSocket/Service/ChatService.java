package com.kibuti.socketserver.WebSocket.Service;


import com.kibuti.socketserver.WebSocket.Model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Sends a message to a specific user based on their userId
     * The user must be subscribed to /topic/messages/{userId} to receive the message
     */
    public void sendPrivateMessage(ChatMessage chatMessage) {
        // Destination format: /topic/messages/{userId}
        String destination = "/topic/messages/" + chatMessage.getRecipientId();

        // Send the message to the destination
        messagingTemplate.convertAndSend(destination, chatMessage);
    }

    /**
     * Convenience method to create and send a chat message in one step
     */
    public void sendDirectMessage(UUID senderId, UUID recipientId, String content) {
        ChatMessage message = new ChatMessage(senderId, recipientId, content);
        sendPrivateMessage(message);
    }
}