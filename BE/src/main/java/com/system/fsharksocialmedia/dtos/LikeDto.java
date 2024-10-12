package com.system.fsharksocialmedia.dtos;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Like}
 */
@Value
public class LikeDto implements Serializable {
    Integer id;
    UserDto username;
    PostDto post;
    CommentDto comment;
    Instant createdate;
}