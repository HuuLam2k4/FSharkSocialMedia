package com.system.fsharksocialmedia.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Postimage}
 */
@Value
public class PostimageDto implements Serializable {
    Integer id;
    PostDto postid;
    ImageDto image;
}