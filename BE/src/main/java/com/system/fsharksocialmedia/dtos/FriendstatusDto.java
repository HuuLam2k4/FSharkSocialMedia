package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Friendstatus}
 */
@Value
public class FriendstatusDto implements Serializable {
    Integer id;
    @Size(max = 100)
    String status;
}