package com.pms.dto.auth;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponceDto {
    private String name ;
    private String email ;
    private String role ;
    private String token ;
    private String message ;
}
