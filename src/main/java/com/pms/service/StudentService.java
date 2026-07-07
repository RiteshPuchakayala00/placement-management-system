package com.pms.service;

import com.pms.dto.student.StudentProfileRequestDto;
import com.pms.dto.student.StudentProfileResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    StudentProfileResponseDto createProfile(StudentProfileRequestDto request) ;
    StudentProfileResponseDto getProfile() ;
    StudentProfileResponseDto updateProfile(StudentProfileRequestDto request) ;
    StudentProfileResponseDto uploadResume(MultipartFile file) ;
}
