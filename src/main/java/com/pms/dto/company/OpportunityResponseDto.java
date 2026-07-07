package com.pms.dto.company;

import com.pms.enums.OpportunityType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OpportunityResponseDto {
    private Long opportunityId ;
    private Long companyId ;
    private String companyName ;
    private String title ;
    private String description ;
    private OpportunityType type ;
    private BigDecimal salaryStipend ;
    private Double requiredCGPA ;
    private LocalDate deadline ;
    private LocalDateTime createdAt ;
}
