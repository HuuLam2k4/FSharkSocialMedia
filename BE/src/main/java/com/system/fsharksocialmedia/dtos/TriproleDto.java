package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Triprole}
 */
@Value
public class TriproleDto implements Serializable {
    Integer id;
    @Size(max = 100)
    String role;
}