package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Message}
 */
@Value
public class MessageDto implements Serializable {
    Integer id;
    ConversationDto conversation;
    UserDto usersrc;
    @Size(max = 500)
    String content;
    Instant createdate;
}