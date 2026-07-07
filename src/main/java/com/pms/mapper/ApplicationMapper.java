package com.pms.mapper;

import com.pms.dto.application.ApplicationResponseDto;
import com.pms.entity.Application;

public class ApplicationMapper {
    private ApplicationMapper(){} ;

    public static ApplicationResponseDto mapToResponse(Application application) {
        if(application == null) return null ;

        return ApplicationResponseDto.builder()
                .applicationId(application.getApplicationId())
                .opportunityId(application.getOpportunity().getOpportunityId())
                .opportunityTitle(application.getOpportunity().getTitle())
                .companyName(application.getOpportunity().getCompany().getCompanyName())
                .studentId(application.getStudent().getStudentId())
                .studentResumeUrl(application.getStudent().getResumeUrl())
                .studentCgpa(application.getStudent().getCgpa())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();
    }
}
