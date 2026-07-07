package com.pms.controller;

import com.pms.dto.company.CompanyProfileRequestDto;
import com.pms.dto.company.CompanyProfileResponseDto;
import com.pms.dto.company.OpportunityRequestDto;
import com.pms.dto.company.OpportunityResponseDto;
import com.pms.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService ;

    @PostMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_COMPANY')")
    public ResponseEntity<CompanyProfileResponseDto> createProfile(
            @Valid @RequestBody CompanyProfileRequestDto request){
        return ResponseEntity.ok(companyService.createProfile(request)) ;
    }
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_COMPANY')")
    public ResponseEntity<CompanyProfileResponseDto> getProfile(){
        return ResponseEntity.ok(companyService.getProfile()) ;
    }
    @PutMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_COMPANY')")
    public ResponseEntity<CompanyProfileResponseDto> updateProfile(
            @Valid @RequestBody CompanyProfileRequestDto request){
        return ResponseEntity.ok(companyService.updateProfile(request)) ;
    }

    @PostMapping("/opportunities")
    @PreAuthorize("hasAuthority('ROLE_COMPANY')")
    public ResponseEntity<OpportunityResponseDto> postOppurtunity(
            @Valid @RequestBody OpportunityRequestDto request){
        return ResponseEntity.ok(companyService.postOpportunity(request)) ;
    }
    @GetMapping("/opportunities")
    @PreAuthorize("hasAuthority('ROLE_COMPANY')")
    public ResponseEntity<List<OpportunityResponseDto>> getOppurtunitiesByCompany(){
        return ResponseEntity.ok(companyService.getOpportunitiesByCompany()) ;
    }
}
