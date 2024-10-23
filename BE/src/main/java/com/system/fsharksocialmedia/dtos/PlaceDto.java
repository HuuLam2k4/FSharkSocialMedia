package com.system.fsharksocialmedia.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.system.fsharksocialmedia.entities.Place}
 */
@Data
public class PlaceDto implements Serializable {
    private Integer id;  // ID - Primary Key

    @Size(max = 500)
    private String nameplace;  // NAMEPLACE - Tên địa điểm

    @Size(max = 100)
    private String longitude;  // LONGITUDE - Kinh độ

    @Size(max = 100)
    private String latitude;  // LATITUDE - Vĩ độ

    @Size(max = 1000)
    private String urlmap;  // URLMAP - Liên kết bản đồ

    @Size(max = 1000)
    private String address;  // ADDRESS - Địa chỉ

    @Size(max = 1000)
    private String description;  // DESCRIPTION - Mô tả
}
