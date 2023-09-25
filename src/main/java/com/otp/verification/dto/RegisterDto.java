package com.otp.verification.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
}
