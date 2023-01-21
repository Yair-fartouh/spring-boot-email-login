package com.example.springbootmailsecurity.email;

public interface EmailSender {
    void send(String to, String email);
}
