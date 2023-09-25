package com.otp.verification.controller;

import com.otp.verification.dto.LoginDto;
import com.otp.verification.dto.OtpRequestDto;
import com.otp.verification.dto.OtpVerificationDto;
import com.otp.verification.dto.RegisterDto;
import com.otp.verification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> register(@RequestBody RegisterDto dto){
        try {
            var user = userService.registerUser(dto);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/api/verify-account", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Object> verifyAccount(@RequestBody OtpVerificationDto dto) {
        try {
            var user = userService.verifyAccount(dto);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/api/regenerate-otp", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Object> regenerateOtp(@RequestBody OtpRequestDto otpRequestDto) {
        try {
            var user = userService.regenerateOtp(otpRequestDto);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> login(@RequestBody LoginDto dto) {
        try {
            var user = userService.login(dto);
            return ResponseEntity.status(200).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
