package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Image}
 */
@Value
public class ImageDto implements Serializable {
    Integer id;
    @Size(max = 500)
    String image;
}