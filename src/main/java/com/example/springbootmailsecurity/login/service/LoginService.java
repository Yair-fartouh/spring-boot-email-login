package com.example.springbootmailsecurity.login.service;

import com.example.springbootmailsecurity.appuser.AppUser;
import com.example.springbootmailsecurity.appuser.repository.AppUserRepository;
import com.example.springbootmailsecurity.login.request.LoginRequest;
import com.example.springbootmailsecurity.registration.EmailValidator;
import com.example.springbootmailsecurity.registration.token.ConfirmationToken;
import com.example.springbootmailsecurity.registration.token.service.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserRepository appUserRepository;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String login(LoginRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        Optional<AppUser> byEmail = appUserRepository.findByEmail(request.getEmail());
        boolean userExists = byEmail.isPresent();
        if (!userExists) {
            throw new IllegalStateException("Email or password do not match");
        }

        //String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        //System.out.println("1 " + encodedPassword);
        //System.out.println("2 " + byEmail.get().getPassword());
        if (!bCryptPasswordEncoder.matches(request.getPassword(), byEmail.get().getPassword())) {
            throw new IllegalStateException("Email or password do not match");
        }
        Long id = byEmail.get().getId();
        ConfirmationToken confirmationToken = confirmationTokenService.getTokenByAppUserId(id)
                .orElseThrow(() ->
                        new IllegalStateException("id not found"));
        //TODO: Return another web page.
        return confirmationToken.getToken();
    }
}
