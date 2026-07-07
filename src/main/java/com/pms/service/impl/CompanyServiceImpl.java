package com.pms.service.impl;

import com.pms.dto.company.CompanyProfileRequestDto;
import com.pms.dto.company.CompanyProfileResponseDto;
import com.pms.dto.company.OpportunityRequestDto;
import com.pms.dto.company.OpportunityResponseDto;
import com.pms.entity.Company;
import com.pms.entity.Opportunity;
import com.pms.entity.User;
import com.pms.enums.ApprovalStatus;
import com.pms.exception.ResourceAlreadyExistsException;
import com.pms.exception.ResourceNotFoundException;
import com.pms.mapper.CompanyMapper;
import com.pms.mapper.OpportunityMapper;
import com.pms.repository.CompanyRepository;
import com.pms.repository.OpportunityRepository;
import com.pms.repository.UserRepository;
import com.pms.security.SecurityUtility;
import com.pms.service.CompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository ;
    private final UserRepository userRepository ;
    private final OpportunityRepository opportunityRepository ;

    private User getAuthenticatedUser(){
        Long userId = SecurityUtility.getCurrentUser().getUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not fount in database")) ;
    }

    @Override
    public CompanyProfileResponseDto createProfile(CompanyProfileRequestDto request) {
        User user = getAuthenticatedUser() ;
        if(user.getCompany() != null) {
            throw new ResourceAlreadyExistsException("This user already has a company profile!") ;
        }
        Company company = Company.builder()
                .user(user)
                .companyName(request.getCompanyName())
                .website(request.getWebsite())
                .build() ;
        Company saveCompany = companyRepository.save(company);
        return CompanyMapper.mapToResponse(saveCompany) ;
    }

    @Override
    public CompanyProfileResponseDto getProfile() {
        User user = getAuthenticatedUser() ;
        if(user.getCompany() == null) {
            throw new ResourceNotFoundException("Company profile not found") ;
        }
        return CompanyMapper.mapToResponse(user.getCompany()) ;
    }

    @Override
    public CompanyProfileResponseDto updateProfile(CompanyProfileRequestDto request) {
        User user = getAuthenticatedUser() ;
        Company company = user.getCompany() ;
        if(company == null) {
            throw new ResourceNotFoundException("Company profile not found.") ;
        }
        company.setCompanyName(request.getCompanyName());
        company.setWebsite(request.getWebsite());
        Company saveCompany = companyRepository.save(company);
        return CompanyMapper.mapToResponse(saveCompany) ;
    }

    @Override
    public OpportunityResponseDto postOpportunity(OpportunityRequestDto request) {
        User user = getAuthenticatedUser();
        Company company = user.getCompany() ;
        if(company == null) {
            throw new ResourceNotFoundException("Company profile not found.Please Create one first") ;
        }
        if(company.getApprovalStatus() != ApprovalStatus.APPROVED){
            throw new RuntimeException("Your company profile must be approved by an Admin before you can post oppotunities.");
        }
        Opportunity opportunity = Opportunity.builder()
                .company(company)
                .title(request.getTitle())
                .description(request.getDescription())
                .type(request.getType())
                .salaryStipend(request.getSalaryStipend())
                .requiredCGPA(request.getRequiredCGPA())
                .deadline(request.getDeadline())
                .build() ;
        Opportunity saveOpportunity = opportunityRepository.save(opportunity);
        return OpportunityMapper.mapToResponse(saveOpportunity) ;
    }

    @Override
    public List<OpportunityResponseDto> getOpportunitiesByCompany() {
        User user = getAuthenticatedUser();
        Company company = user.getCompany() ;
        if(company == null) {
            throw new ResourceNotFoundException("Company profile not found.Please Create one first") ;
        }
        return company.getOpportunities().stream()
                .map(OpportunityMapper::mapToResponse)
                .toList() ;
    }
}
