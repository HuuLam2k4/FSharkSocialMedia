package com.system.fsharksocialmedia.models;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
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
