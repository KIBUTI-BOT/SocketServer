package com.kibuti.socketserver.WebSocket.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private UUID id;
    private UUID senderId;
    private UUID recipientId;
    private String content;
    private Date timestamp;
    private MessageStatus status;

    public enum MessageStatus {
        SENT,
        DELIVERED,
        READ
    }

    // Constructor with auto-generated values
    public ChatMessage(UUID senderId, UUID recipientId, String content) {
        this.id = UUID.randomUUID();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
        this.timestamp = new Date();
        this.status = MessageStatus.SENT;
    }
}
