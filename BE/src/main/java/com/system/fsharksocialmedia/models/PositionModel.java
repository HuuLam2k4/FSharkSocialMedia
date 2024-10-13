package com.system.fsharksocialmedia.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PositionModel {
    Integer id;

    @NotBlank(message = "Vui lòng nhập chức vụ!")
    @Size(min = 1, max = 100, message = "Chức vụ phải có độ dài từ 1 đến 100 ký tự!")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Chức vụ chỉ được chứa chữ cái và số!")
    String positionName;
}
