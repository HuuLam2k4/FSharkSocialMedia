package com.system.fsharksocialmedia.dtos;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@Value
public class GroupmemberDto implements Serializable {
    Integer id;
    ConversationDto conversation;
    UserDto username;
    Instant timejoin;
}