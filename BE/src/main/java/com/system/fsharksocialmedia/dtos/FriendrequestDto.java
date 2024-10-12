package com.system.fsharksocialmedia.dtos;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Friendrequest}
 */
@Value
public class FriendrequestDto implements Serializable {
    Integer id;
    UserDto sender;
    UserDto usersrc;
    Instant createdate;
    FriendstatusDto status;
}