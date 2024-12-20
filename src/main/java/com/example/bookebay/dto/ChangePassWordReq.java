package com.example.bookebay.dto;

import lombok.Data;

@Data
public class ChangePassWordReq {
    private String currentPassword;
    private String newPassword;
}
