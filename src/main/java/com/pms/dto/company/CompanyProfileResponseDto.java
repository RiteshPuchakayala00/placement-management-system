package com.pms.dto.company;

import com.pms.enums.ApprovalStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyProfileResponseDto {
    private Long companyId ;
    private Long userId ;
    private String name ;
    private String email ;
    private String companyName ;
    private String website ;
    private ApprovalStatus approvalStatus ;
}
