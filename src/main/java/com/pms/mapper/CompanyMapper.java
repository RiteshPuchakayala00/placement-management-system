package com.pms.mapper;

import com.pms.dto.company.CompanyProfileResponseDto;
import com.pms.entity.Company;

public class CompanyMapper {
    private CompanyMapper(){
    }

    public static CompanyProfileResponseDto mapToResponse(Company company) {
        if(company == null) return null ;
        return CompanyProfileResponseDto.builder()
                .companyId(company.getCompanyId())
                .userId(company.getUser().getUserId())
                .name(company.getUser().getName())
                .email(company.getUser().getEmail())
                .companyName(company.getCompanyName())
                .website(company.getWebsite())
                .approvalStatus(company.getApprovalStatus())
                .build();
    }
}
