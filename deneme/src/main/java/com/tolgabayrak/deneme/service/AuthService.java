package com.tolgabayrak.deneme.service;

import com.tolgabayrak.deneme.dto.LoginDto;
import com.tolgabayrak.deneme.dto.RegisterDto;
import com.tolgabayrak.deneme.model.User;
import com.tolgabayrak.deneme.repository.UserRepository;
import com.tolgabayrak.deneme.util.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Helper helper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, Helper helper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.helper = helper;
    }

    public User create(RegisterDto registerDto){
        User user = new User(registerDto.getEmail(), registerDto.getPassword());
        return userRepository.save(user);
    }

    public HashMap<String, String> login(LoginDto loginDto){
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if (user.isPresent()){
            String accessToken = this.helper.generateAccessToken("Deneme", user.get().getId());
            String refreshToken = this.helper.generateRefreshToken("Deneme");
            HashMap<String, String> tokens = new HashMap<String, String>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);
            return tokens;
        }else {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }




}
