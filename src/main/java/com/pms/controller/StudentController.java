package com.pms.controller;

import com.pms.dto.student.StudentProfileRequestDto;
import com.pms.dto.student.StudentProfileResponseDto;
import com.pms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/students/profile")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<StudentProfileResponseDto> createProfile(
            @Valid @RequestBody StudentProfileRequestDto request) {
        StudentProfileResponseDto response = studentService.createProfile(request);
        return ResponseEntity.ok(response) ;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<StudentProfileResponseDto> getProfile() {
        return ResponseEntity.ok(studentService.getProfile()) ;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<StudentProfileResponseDto> updateProfile(
            @Valid @RequestBody StudentProfileRequestDto request
    ) {
        return ResponseEntity.ok(studentService.updateProfile(request)) ;
    }

    @PostMapping("/resume")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public ResponseEntity<StudentProfileResponseDto> uploadResume(
            @RequestParam("file")MultipartFile file
    ) {
        return ResponseEntity.ok(studentService.uploadResume(file)) ;
    }


}
