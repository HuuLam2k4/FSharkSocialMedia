package com.system.fsharksocialmedia.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Usertrip}
 */
@Value
public class UsertripDto implements Serializable {
    Integer id;
    TripDto tripid;
    TriproleDto role;
}