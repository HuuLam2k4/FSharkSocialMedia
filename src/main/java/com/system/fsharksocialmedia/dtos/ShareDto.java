package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Share}
 */
@Value
public class ShareDto implements Serializable {
    Integer id;
    UserDto username;
    PostDto post;
    @Size(max = 500)
    String content;
    Instant createdate;
}