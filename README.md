# Spring Boot OTP Verification Email

## Overview

This application is an example implementation of OTP (One-Time Password) verification using Java Spring Boot. OTP verification is sent via email to registered users. To send emails, the application utilizes Java Mail Sender, which leverages the Simple Mail Transfer Protocol (SMTP) to send emails to the recipient addresses.

## API Endpoints

### 1. User Registration

- **Endpoint**: `POST http://localhost:8080/api/register`
- **Request Body**:
  ```json
  {
      "name": "example",
      "email": "example@gmail.com",
      "password": "example"
  }

### 2. Verify Account

- **Endpoint**: `POST http://localhost:8080/api/verify-account
- **Request Body**:
  ```json
  {
      "email": "example@gmail.com",
      "otp": "111111"
  }

### 3. Regenerate OTP

- **Endpoint**: `POST http://localhost:8080/api/regenerate-otp
- **Request Body**:
  ```json
  {
      "email": "example@gmail.com"
  }

### 4. Login

- **Endpoint**: `POST http://localhost:8080/api/login
- **Request Body**:
  ```json
  {
      "email": "example@gmail.com"
      "password": "123456"
  }

## How to Use the Application

1. **Register a User Account:**  
   Register a user account with your email address using the User Registration endpoint.

2. **Receive OTP via Email:**  
   After registering, you will receive an email containing an OTP (One-Time Password).

3. **Verify Your Account:**  
   Verify your account by providing the OTP sent to your email using the Verify Account endpoint.

4. **Regenerate OTP (Optional):**  
   If needed, you can regenerate a new OTP for your account using the Regenerate OTP endpoint.

5. **Log In:**  
   You can log in using your email and password using the User Login endpoint.


## Email Configuration

You need to configure your email settings in `application.properties` to send OTPs. Make sure to replace `spring.mail.host`, `spring.mail.port`, `spring.mail.username`, and `spring.mail.password` with your email credentials.

## Notes

- This application is a simple example and is provided for learning and demonstration purposes.
- To enable this application for production use, you should consider additional security and best practices, including secure storage for OTPs and overall application security.

Thank you for using this application! For further information, please refer to the Spring Boot and Spring Mail Sender documentation.
