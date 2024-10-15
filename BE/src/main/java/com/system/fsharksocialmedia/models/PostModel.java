package com.system.fsharksocialmedia.models;

import com.system.fsharksocialmedia.dtos.UserDto;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;

@Data
public class PostModel {
    @Size(max = 200)
    String content;

}
