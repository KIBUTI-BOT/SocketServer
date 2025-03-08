package com.kibuti.socketserver.WebSocket.Controller;

import com.kibuti.socketserver.WebSocket.Model.ChatMessage;
import com.kibuti.socketserver.WebSocket.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Handles messages sent to /app/chat.send
     * Forwards the message to the specific user's topic
     */
    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        chatService.sendPrivateMessage(chatMessage);

        // You could also store the message in a database here
    }

    /**
     * Handles messages sent to /app/chat.notify
     * Used for typing indicators, read receipts, etc.
     */
    @MessageMapping("/chat.notify")
    public void notifyUser(@Payload ChatMessage chatMessage) {
        // Here you could handle status updates, typing indicators, etc.
        String destination = "/topic/notifications/" + chatMessage.getRecipientId();
        messagingTemplate.convertAndSend(destination, chatMessage);
    }
}