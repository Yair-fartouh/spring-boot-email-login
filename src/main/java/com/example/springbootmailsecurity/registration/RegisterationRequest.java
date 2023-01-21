package com.example.springbootmailsecurity.registration;

import lombok.*;

@Data
public class RegisterationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
