package com.pms.service;

import com.pms.dto.auth.LoginRequestDto;
import com.pms.dto.auth.LoginResponceDto;
import com.pms.dto.auth.RegisterRequestDto;
import com.pms.dto.auth.RegisterResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    RegisterResponseDto register(RegisterRequestDto request) ;

    LoginResponceDto login(LoginRequestDto request) ;
}
