package com.pms.dto.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {
    private String name ;
    private String email ;
    private String role ;
    private String message ;
}
