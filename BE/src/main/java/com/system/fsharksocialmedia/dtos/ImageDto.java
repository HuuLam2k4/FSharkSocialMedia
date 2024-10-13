package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Data
public class ImageDto implements Serializable {
    Integer id;
    @Size(max = 500)
    String image;
}