package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Notification}
 */
@Value
public class NotificationDto implements Serializable {
    Integer id;
    UserDto username;
    @Size(max = 500)
    String content;
    TypeDto type;
    PostDto post;
    Boolean status;
    Instant createdate;
}