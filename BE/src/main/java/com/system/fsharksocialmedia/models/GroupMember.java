package com.system.fsharksocialmedia.models;

import com.system.fsharksocialmedia.dtos.ConversationDto;
import com.system.fsharksocialmedia.dtos.UserDto;
import lombok.Data;

import java.time.Instant;

@Data
public class GroupMember {
    Integer id;
    ConversationDto conversation;
    UserDto username;
    Instant timejoin;
}
