package com.pms.controller;

import com.pms.dto.application.ApplicationResponseDto;
import com.pms.enums.ApplicationStatus;
import com.pms.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService ;

    @PostMapping("/apply/{opportunityId}")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<ApplicationResponseDto> applyForOpportunity(
            @PathVariable Long opportunityId
    ) {
        return ResponseEntity.ok(applicationService.applyForOpportunity(opportunityId)) ;
    }

    @GetMapping("/student")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<List<ApplicationResponseDto>> getMyApplications() {
        return ResponseEntity.ok(applicationService.getMyApplication()) ;
    }

    @GetMapping("/opportunity/{opportunityId}")
    @PreAuthorize("hasAuthority('ROLE_COMPANY')")
    public ResponseEntity<List<ApplicationResponseDto>> getApplicationsForOpportunity(
            @PathVariable Long opportunityId) {
        return ResponseEntity.ok(applicationService.getApplicationsForOpportunity(opportunityId)) ;
    }

    @PutMapping("/{applicationId}")
    @PreAuthorize("hasAuthority('ROLE_COMPANY')")
    public ResponseEntity<ApplicationResponseDto> updateApplicationStatus(
            @PathVariable Long applicationId ,
            @RequestParam ApplicationStatus status
            ) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId,status)) ;
    }

}
