package com.system.fsharksocialmedia.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Cmtimage}
 */
@Value
public class CmtimageDto implements Serializable {
    Integer id;
    CommentDto cmtid;
    ImageDto image;
}