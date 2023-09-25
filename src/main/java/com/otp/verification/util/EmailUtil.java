package com.otp.verification.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendOtpEmail(String email, String otp) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Verification Code for Secure Access");

    String emailContent = """
        <html>
        <body>
            <h2>OTP Verification</h2>
            <p>Hello there!</p>
            <p>Your One-Time Password (OTP) for secure access is:</p>
            <p style="font-size: 24px; font-weight: bold; color: #007bff;">%s</p>
            <p>Please use this OTP to complete your login process.</p>
            <p>If you did not request this OTP, please ignore this email.</p>
            <p>Best regards,</p>
            <p>Your Application Team</p>
        </body>
        </html>
        """.formatted(otp);

    mimeMessageHelper.setText(emailContent, true);

    javaMailSender.send(mimeMessage);
  }

}
