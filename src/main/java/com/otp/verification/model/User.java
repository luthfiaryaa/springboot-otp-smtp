package com.otp.verification.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Email")
  private String email;

  @Column(name = "Password")
  private String password;

  @Column(name = "IsActive")
  private boolean active;

  @Column(name = "Otp")
  private String otp;

  @Column(name = "OtpGeneratedTime")
  private LocalDateTime otpGeneratedTime;

  public User(Integer id, String name, String email, String password, String otp, LocalDateTime otpGeneratedTime) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.otp = otp;
    this.otpGeneratedTime = otpGeneratedTime;
  }
}
