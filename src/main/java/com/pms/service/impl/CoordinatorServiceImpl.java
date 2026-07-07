package com.pms.service.impl;

import com.pms.dto.company.CompanyProfileResponseDto;
import com.pms.dto.coordinator.CoordinatorProfileRequestDto;
import com.pms.dto.coordinator.CoordinatorProfileResponseDto;
import com.pms.entity.Company;
import com.pms.entity.Coordinator;
import com.pms.entity.User;
import com.pms.enums.ApprovalStatus;
import com.pms.exception.ResourceAlreadyExistsException;
import com.pms.exception.ResourceNotFoundException;
import com.pms.mapper.CompanyMapper;
import com.pms.mapper.CoordinatorMapper;
import com.pms.repository.CompanyRepository;
import com.pms.repository.CoordinatorRepository;
import com.pms.repository.UserRepository;
import com.pms.security.SecurityUtility;
import com.pms.service.CoordinatorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CoordinatorServiceImpl implements CoordinatorService {
    private final CoordinatorRepository coordinatorRepository ;
    private final UserRepository userRepository ;
    private final CompanyRepository companyRepository;

    private User getAuthenticatedUser() {
        Long userId = SecurityUtility.getCurrentUser().getUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not fount in database")) ;
    }
    @Override
    public CoordinatorProfileResponseDto createProfile(CoordinatorProfileRequestDto request) {
        User user = getAuthenticatedUser() ;
        if(user.getCoordinator() != null) {
            throw new ResourceAlreadyExistsException("This user already has a coordinator profile!") ;
        }

        Coordinator coordinator = Coordinator.builder()
                .user(user)
                .designation(request.getDesignation())
                .build() ;
        Coordinator saveCoordinator = coordinatorRepository.save(coordinator) ;
        return CoordinatorMapper.mapToResponse(saveCoordinator) ;
    }

    @Override
    public CoordinatorProfileResponseDto getProfile() {
        User user = getAuthenticatedUser() ;
        Coordinator coordinator = user.getCoordinator() ;
        if(coordinator == null){
            throw new ResourceNotFoundException("Coordinator not found") ;
        }
        return CoordinatorMapper.mapToResponse(coordinator) ;
    }

    @Override
    public List<CompanyProfileResponseDto> getPendingCompanies() {
        User user = getAuthenticatedUser() ;
        List<Company> pendingCompanies = companyRepository.findByApprovalStatus(ApprovalStatus.PENDING) ;
        return pendingCompanies.stream()
                .map(CompanyMapper::mapToResponse)
                .toList() ;
    }

    @Override
    public CompanyProfileResponseDto updateCompanyStatus(Long companyId, ApprovalStatus status) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Company not found!")
                );
        company.setApprovalStatus(status);
        Company saveCompany = companyRepository.save(company) ;
        return CompanyMapper.mapToResponse(saveCompany) ;
    }
}
