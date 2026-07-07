package com.pms.service;

import com.pms.dto.application.ApplicationResponseDto;
import com.pms.enums.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    ApplicationResponseDto applyForOpportunity(Long opportunityId) ;
    List<ApplicationResponseDto> getMyApplication() ;
    List<ApplicationResponseDto> getApplicationsForOpportunity(Long opportunityID) ;
    ApplicationResponseDto updateApplicationStatus(Long applicationId, ApplicationStatus status) ;
}

