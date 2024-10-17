package com.system.fsharksocialmedia.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document(collection = "Messages")
public class MessageModel {
    MessageType type;
    String sender;
    String recipient;
    String content;
    Instant time;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
