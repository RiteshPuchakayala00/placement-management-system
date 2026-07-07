package com.pms.dto.coordinator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatorProfileResponseDto {
    private Long coordinatorId ;
    private Long userId ;
    private String name ;
    private String email ;
    private String designation ;
}
