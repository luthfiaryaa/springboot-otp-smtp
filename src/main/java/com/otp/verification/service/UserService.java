package com.otp.verification.service;

import com.otp.verification.dto.LoginDto;
import com.otp.verification.dto.OtpRequestDto;
import com.otp.verification.dto.OtpVerificationDto;
import com.otp.verification.dto.RegisterDto;
import com.otp.verification.model.User;
import com.otp.verification.repository.UserRepository;
import com.otp.verification.util.EmailUtil;
import com.otp.verification.util.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private UserRepository userRepository;

    public String registerUser(RegisterDto dto) {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(dto.getEmail(), otp);
        } catch (MessagingException me) {
            throw new RuntimeException("Unable to send otp please try again");
        }

        var user = new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword(), otp, LocalDateTime.now());
        userRepository.save(user);
        return "User registration successful, Please verify your OTP";
    }

    public String verifyAccount(OtpVerificationDto dto) {
        var user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() ->
                new RuntimeException("User not found with this email: " + dto.getEmail()));
        if (user.getOtp().equals(dto.getOtp()) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (60)) {
            user.setActive(true);
            userRepository.save(user);
            return "OTP Verified, you can loggin";
        }
        return "Please regenerate otp and try again";
    }

    public String regenerateOtp(OtpRequestDto otpReq) {
        var user = userRepository.findByEmail(otpReq.getEmail()).orElseThrow(() ->
                new RuntimeException("User not found with this email: " + otpReq.getEmail()));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(otpReq.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }

    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
        if (!loginDto.getPassword().equals(user.getPassword())) {
            return "Password is incorrect";
        } else if (!user.isActive()) {
            return "your account is not verified";
        }
        return "Login successful";
    }
}
