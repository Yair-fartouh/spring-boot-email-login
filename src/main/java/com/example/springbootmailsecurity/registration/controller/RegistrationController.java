package com.example.springbootmailsecurity.registration.controller;

import com.example.springbootmailsecurity.login.request.LoginRequest;
import com.example.springbootmailsecurity.login.service.LoginService;
import com.example.springbootmailsecurity.registration.RegisterationRequest;
import com.example.springbootmailsecurity.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping
    public String register(@RequestBody RegisterationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        //registrationService.addTokenToCookie(token);
        return registrationService.confirmToken(token);
    }
    @GetMapping(path = "userinfo")
    public ResponseEntity<String> conm(HttpServletRequest request) {
        return  ResponseEntity.ok().body(request.getHeader("Authorization"));
    }
}
