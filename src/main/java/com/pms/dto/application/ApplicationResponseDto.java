package com.pms.dto.application;

import com.pms.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDto {
    private Long applicationId ;
    private Long opportunityId ;
    private String opportunityTitle ;
    private String companyName ;
    private Long studentId ;
    private String studentName ;
    private String studentResumeUrl ;
    private Double studentCgpa ;
    private ApplicationStatus status ;
    private LocalDateTime appliedAt ;
}
