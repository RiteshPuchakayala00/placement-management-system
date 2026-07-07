package com.pms.mapper;

import com.pms.dto.company.OpportunityResponseDto;
import com.pms.entity.Opportunity;

public class OpportunityMapper {
    private OpportunityMapper(){}

    public static OpportunityResponseDto mapToResponse(Opportunity opportunity){
        if(opportunity == null) return null ;
        return OpportunityResponseDto.builder()
                .opportunityId(opportunity.getOpportunityId())
                .companyId(opportunity.getCompany().getCompanyId())
                .companyName(opportunity.getCompany().getCompanyName())
                .title(opportunity.getTitle())
                .description(opportunity.getDescription())
                .type(opportunity.getType())
                .salaryStipend(opportunity.getSalaryStipend())
                .requiredCGPA(opportunity.getRequiredCGPA())
                .deadline(opportunity.getDeadline())
                .createdAt(opportunity.getCreatedAt())
                .build() ;
    }
}
