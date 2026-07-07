package com.pms.dto.student;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileResponseDto {
    private Long studentId ;
    private Long userId ;
    private String rollNo;
    private String name;
    private String email;
    private String department;
    private Double cgpa;
    private String phoneNo;
    private Integer graduationYear;
    private List<String> skills;
    private String resumeUrl ;
}
