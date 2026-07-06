package com.pms.service;

import org.springframework.stereotype.Service;

public interface JwtService {
    String generateToken(String email) ;
    boolean validateToken(String token , String email) ;
    String extractEmail(String token) ;
}
