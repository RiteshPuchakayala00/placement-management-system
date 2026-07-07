package com.pms.mapper;

import com.pms.dto.student.StudentProfileResponseDto;
import com.pms.entity.Skill;
import com.pms.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    private StudentMapper() {
    }

    public static StudentProfileResponseDto mapToResponse(Student student) {
        List<String> skillNames = student.getSkills()
                .stream()
                .map(Skill::getName)
                .toList() ;

        return StudentProfileResponseDto.builder()
                .studentId(student.getStudentId())
                .userId(student.getUser().getUserId())
                .rollNo(student.getRollNo())
                .name(student.getUser().getName())
                .email(student.getUser().getEmail())
                .department(student.getDepartment())
                .cgpa(student.getCgpa())
                .phoneNo(student.getPhone())
                .graduationYear(student.getGraduationYear())
                .skills(skillNames)
                .resumeUrl(student.getResumeUrl())
                .build() ;
    }
}
