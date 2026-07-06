package com.pms.service.impl;

import com.pms.dto.auth.LoginRequestDto;
import com.pms.dto.auth.LoginResponceDto;
import com.pms.dto.auth.RegisterRequestDto;
import com.pms.dto.auth.RegisterResponseDto;
import com.pms.entity.User;
import com.pms.exception.InvalidCredentialsException;
import com.pms.exception.ResourceAlreadyExistsException;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.UserRepository;
import com.pms.service.AuthService;
import com.pms.service.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository ;
    private final JwtService jwtService ;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    private final BCryptPasswordEncoder passwordEncoder ;



    @Override
    public RegisterResponseDto register(RegisterRequestDto request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new ResourceAlreadyExistsException(
                    "Email already registered"
            );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .role(request.getRole())
                .build() ;

        userRepository.save(user) ;

        return RegisterResponseDto.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(String.valueOf(request.getRole()))
                .message("User Registered Successfully")
                .build() ;

    }

    @Override
    public LoginResponceDto login(LoginRequestDto request) {
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User Not Found"
                        )
                );

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException(
                    "Invalid email or password"
            ) ;
        }

        String token = jwtService.generateToken(user.getEmail()) ;

        return LoginResponceDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .token(token)
                .message("User LoggedIn Successfully")
                .build() ;
    }
}
