package com.system.fsharksocialmedia.models;

import com.system.fsharksocialmedia.dtos.ConversationDto;
import com.system.fsharksocialmedia.dtos.UserDto;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
@Data
public class MessageModel {
    Integer id;
    ConversationDto conversation;
    UserDto usersrc;
    String content;
    Instant createdate;
}
