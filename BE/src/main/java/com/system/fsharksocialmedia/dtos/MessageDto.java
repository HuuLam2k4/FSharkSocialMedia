package com.system.fsharksocialmedia.dtos;

import com.system.fsharksocialmedia.entities.Conversation;
import com.system.fsharksocialmedia.entities.User;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Data
public class MessageDto implements Serializable {
    Integer id;
    ConversationDto conversation;
    UserDto usersrc;
    @Size(max = 500)
    String content;
    Instant createdate;
}