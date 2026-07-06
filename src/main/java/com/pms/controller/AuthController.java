package com.pms.controller;

import com.pms.dto.auth.LoginRequestDto;
import com.pms.dto.auth.LoginResponceDto;
import com.pms.dto.auth.RegisterRequestDto;
import com.pms.dto.auth.RegisterResponseDto;
import com.pms.service.AuthService;
import com.pms.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {
        RegisterResponseDto response = authService.register(request) ;
        return ResponseEntity.ok(response) ;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponceDto> login(@Valid @RequestBody LoginRequestDto request) {
        LoginResponceDto response = authService.login(request) ;
        return ResponseEntity.ok(response) ;
    }

    @GetMapping("/test-token")
    public String testToken(@RequestParam String token){
        return jwtService.extractEmail(token) ;
    }

}

