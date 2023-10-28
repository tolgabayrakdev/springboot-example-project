package com.tolgabayrak.deneme.controller;

import com.tolgabayrak.deneme.dto.LoginDto;
import com.tolgabayrak.deneme.dto.RegisterDto;
import com.tolgabayrak.deneme.model.User;
import com.tolgabayrak.deneme.service.AuthService;
import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public HashMap<String, String> login(@RequestBody LoginDto loginDto){
        HashMap<String, String> result = this.authService.login(loginDto);

        Cookie cookie = new Cookie("access_token", result.get("access_token"));
        cookie.isHttpOnly();
        return result;
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(this.authService.create(registerDto));
    }

}
