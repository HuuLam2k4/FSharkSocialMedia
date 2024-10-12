package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Comment}
 */
@Value
public class CommentDto implements Serializable {
    Integer id;
    @Size(max = 500)
    String content;
    UserDto username;
    PostDto post;
    ImageDto image;
}