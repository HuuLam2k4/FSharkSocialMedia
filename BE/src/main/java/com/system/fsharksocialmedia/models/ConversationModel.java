package com.system.fsharksocialmedia.models;

import com.system.fsharksocialmedia.dtos.ImageDto;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
@Data
public class ConversationModel {
    Integer id;
    @Size(max = 300)
    String name;
    Instant createdat;
    ImageDto avatar;
}
