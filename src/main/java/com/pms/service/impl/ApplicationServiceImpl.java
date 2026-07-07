package com.pms.service.impl;

import com.pms.dto.application.ApplicationResponseDto;
import com.pms.entity.*;
import com.pms.enums.ApplicationStatus;
import com.pms.exception.InvalidCredentialsException;
import com.pms.exception.ResourceAlreadyExistsException;
import com.pms.exception.ResourceNotFoundException;
import com.pms.mapper.ApplicationMapper;
import com.pms.repository.ApplicationRepository;
import com.pms.repository.OpportunityRepository;
import com.pms.repository.UserRepository;
import com.pms.security.SecurityUtility;
import com.pms.service.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository ;
    private final OpportunityRepository opportunityRepository ;
    private final UserRepository userRepository ;

    private User getAuthenticatedUser() {
        Long userId = SecurityUtility.getCurrentUser().getUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not fount in database")) ;
    }
    @Override
    public ApplicationResponseDto applyForOpportunity(Long opportunityId) {
        User user = getAuthenticatedUser() ;
        Student student = user.getStudent() ;
        if(student == null) {
            throw new ResourceNotFoundException("You must create a Student Profile before applying!") ;
        }
        Opportunity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found")) ;
        if(student.getCgpa() < opportunity.getRequiredCGPA()) {
            throw new InvalidCredentialsException("You do not meet the required CGPA requirement of " + opportunity.getRequiredCGPA() + " for this role") ;
        }
        if(applicationRepository.existsByStudentAndOpportunity(student , opportunity)) {
            throw new ResourceAlreadyExistsException("You have already applied for this opportunity!") ;
        }
        Application application = Application.builder()
                .student(student)
                .opportunity(opportunity)
                .build();
        Application saveApplication = applicationRepository.save(application) ;
        return ApplicationMapper.mapToResponse(saveApplication) ;
    }


    @Override
    public List<ApplicationResponseDto> getMyApplication() {
        User user = getAuthenticatedUser() ;
        Student student = user.getStudent() ;
        if(student == null) {
            throw new ResourceNotFoundException("You must create a Student Profile before applying!") ;
        }
        return student.getApplications().stream()
                .map(ApplicationMapper::mapToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponseDto> getApplicationsForOpportunity(Long opportunityID) {
        User user = getAuthenticatedUser() ;
        Company company = user.getCompany() ;
        Opportunity opportunity = opportunityRepository.findById(opportunityID)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found")) ;
        if(!opportunity.getCompany().getCompanyId().equals(company.getCompanyId())) {
            throw new RuntimeException("You dont have permission to view there applications.") ;
        }
        return opportunity.getApplicationList().stream()
                .map(ApplicationMapper::mapToResponse)
                .toList();
    }

    @Override
    public ApplicationResponseDto updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        User user = getAuthenticatedUser();
        Company company = user.getCompany();
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found")) ;
        if (!application.getOpportunity().getCompany().getCompanyId().equals(company.getCompanyId())) {
            throw new RuntimeException("You do not have permission to modify this application.");
        }
        application.setStatus(status);
        Application savedApplication = applicationRepository.save(application);
        return ApplicationMapper.mapToResponse(savedApplication);
    }
}
