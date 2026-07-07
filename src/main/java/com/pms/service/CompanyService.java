package com.pms.service;

import com.pms.dto.company.CompanyProfileRequestDto;
import com.pms.dto.company.CompanyProfileResponseDto;
import com.pms.dto.company.OpportunityRequestDto;
import com.pms.dto.company.OpportunityResponseDto;

import java.util.List;

public interface CompanyService {
    CompanyProfileResponseDto createProfile(CompanyProfileRequestDto request) ;
    CompanyProfileResponseDto getProfile() ;
    CompanyProfileResponseDto updateProfile(CompanyProfileRequestDto request) ;
    OpportunityResponseDto postOpportunity(OpportunityRequestDto request) ;
    List<OpportunityResponseDto> getOpportunitiesByCompany() ;
}
