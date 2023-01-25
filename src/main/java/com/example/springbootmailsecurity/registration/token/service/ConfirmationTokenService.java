package com.example.springbootmailsecurity.registration.token.service;

import com.example.springbootmailsecurity.registration.token.ConfirmationToken;
import com.example.springbootmailsecurity.registration.token.ripository.ConfirmationTokenRipository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRipository confirmationTokenRipository;
    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRipository.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRipository.findByToken(token);
    }
    public int setConfirmedAt(String token) {
        return confirmationTokenRipository.updateConfirmedAt(token, LocalDateTime.now());
    }
    public Optional<ConfirmationToken> getTokenByAppUserId(Long id){
        return confirmationTokenRipository.findByAppUserId(id);
    }
}
