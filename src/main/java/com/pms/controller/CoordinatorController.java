package com.pms.controller;

import com.pms.dto.company.CompanyProfileResponseDto;
import com.pms.dto.coordinator.CoordinatorProfileRequestDto;
import com.pms.dto.coordinator.CoordinatorProfileResponseDto;
import com.pms.enums.ApprovalStatus;
import com.pms.service.CoordinatorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinator")
@AllArgsConstructor
public class CoordinatorController {
    private final CoordinatorService coordinatorService;

    @PostMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_COORDINATOR')")
    public ResponseEntity<CoordinatorProfileResponseDto> createProfile(
            @Valid @RequestBody CoordinatorProfileRequestDto request
            ) {
        return ResponseEntity.ok(coordinatorService.createProfile(request)) ;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_COORDINATOR')")
    public ResponseEntity<CoordinatorProfileResponseDto> getProfile(
    ) {
        return ResponseEntity.ok(coordinatorService.getProfile()) ;
    }

    @GetMapping("/approvals")
    @PreAuthorize("hasAuthority('ROLE_COORDINATOR')")
    public ResponseEntity<List<CompanyProfileResponseDto>> getPendingCompanies(){
        return ResponseEntity.ok(coordinatorService.getPendingCompanies()) ;
    }

    @PutMapping("/approvals/{companyId}")
    @PreAuthorize("hasAuthority('ROLE_COORDINATOR')")
    public ResponseEntity<CompanyProfileResponseDto> updateCompanyStatus(
            @PathVariable Long companyId,
            @RequestParam ApprovalStatus status
            ) {
        return ResponseEntity.ok(coordinatorService.updateCompanyStatus(companyId, status));
    }
}
