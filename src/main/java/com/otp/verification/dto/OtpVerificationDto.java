package com.otp.verification.dto;

import lombok.Data;

@Data
public class OtpVerificationDto {
    private String email;
    private String otp;
}
