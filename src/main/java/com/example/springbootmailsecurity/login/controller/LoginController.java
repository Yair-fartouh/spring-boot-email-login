package com.example.springbootmailsecurity.login.controller;

import com.example.springbootmailsecurity.login.request.LoginRequest;
import com.example.springbootmailsecurity.login.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
@AllArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping
    public String register(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
}
