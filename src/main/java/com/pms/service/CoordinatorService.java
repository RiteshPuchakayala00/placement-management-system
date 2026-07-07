package com.pms.service;

import com.pms.dto.company.CompanyProfileResponseDto;
import com.pms.dto.coordinator.CoordinatorProfileRequestDto;
import com.pms.dto.coordinator.CoordinatorProfileResponseDto;
import com.pms.enums.ApprovalStatus;

import java.util.List;

public interface CoordinatorService {
    CoordinatorProfileResponseDto createProfile(CoordinatorProfileRequestDto request) ;
    CoordinatorProfileResponseDto getProfile() ;
    List<CompanyProfileResponseDto> getPendingCompanies() ;
    CompanyProfileResponseDto updateCompanyStatus(Long companyId , ApprovalStatus status) ;
}
