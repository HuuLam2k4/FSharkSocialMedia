package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Conversation}
 */
@Value
public class ConversationDto implements Serializable {
    Integer id;
    @Size(max = 300)
    String name;
    Instant createdat;
    ImageDto avatar;
}