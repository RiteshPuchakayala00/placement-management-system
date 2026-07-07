package com.pms.service.impl;

import com.pms.dto.student.StudentProfileRequestDto;
import com.pms.dto.student.StudentProfileResponseDto;
import com.pms.entity.Skill;
import com.pms.entity.Student;
import com.pms.entity.User;
import com.pms.exception.ResourceAlreadyExistsException;
import com.pms.exception.ResourceNotFoundException;
import com.pms.mapper.StudentMapper;
import com.pms.repository.SkillRepository;
import com.pms.repository.StudentRepository;
import com.pms.repository.UserRepository;
import com.pms.security.SecurityUtility;
import com.pms.service.FileStorageService;
import com.pms.service.StudentService;
import io.jsonwebtoken.security.Message;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository ;
    private final UserRepository userRepository ;
    private final SkillRepository skillRepository ;
    private final FileStorageService fileStorageService ;

    @Override
    public  StudentProfileResponseDto createProfile(StudentProfileRequestDto request) {
        Long currentUserId = SecurityUtility.getCurrentUser().getUser().getUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        studentRepository.findByUser(user)
                .ifPresent(student -> {
                    throw new ResourceAlreadyExistsException(
                            "This user already has a student Profile!"
                    );
                });

        Student student = Student.builder()
                .user(user)
                .rollNo(request.getRollNo())
                .department(request.getDepartment())
                .cgpa(request.getCgpa())
                .phone(request.getPhoneNo())
                .graduationYear(request.getGraduationYear())
                .build() ;

        Set<Skill> studentSkills = new HashSet<>() ;
        if(request.getSkills() != null) {
            for(String skillName : request.getSkills()) {
                Skill skill = skillRepository.findByName(skillName)
                        .orElseGet(
                                () -> skillRepository.save(Skill.builder().name(skillName).build())
                        ) ;
                studentSkills.add(skill) ;
            }
        }
        student.setSkills(studentSkills);
        Student saveStudent = studentRepository.save(student) ;
        return StudentMapper.mapToResponse(saveStudent) ;
    }

    @Override
    public StudentProfileResponseDto getProfile() {
        Long currentUserId = SecurityUtility.getCurrentUser().getUser().getUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = user.getStudent() ;
        if(student == null) {
            throw new ResourceNotFoundException(
                    "Student profile not found. Please create one first"
            ) ;
        }
        return StudentMapper.mapToResponse(student) ;
    }

    @Override
    public StudentProfileResponseDto updateProfile(StudentProfileRequestDto request) {
        Long currentUserId = SecurityUtility.getCurrentUser().getUser().getUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Student student = user.getStudent() ;
        if(student == null) {
            throw new ResourceNotFoundException(
                    "Student profile not found. Please create one first"
            ) ;
        }
        student.setRollNo(request.getRollNo());
        student.setDepartment(request.getDepartment());
        student.setCgpa(request.getCgpa());
        student.setPhone(request.getPhoneNo());
        student.setGraduationYear(request.getGraduationYear());

        student.getSkills().clear();

        if(request.getSkills() != null) {
            for(String skillName : request.getSkills()) {
                Skill skill = skillRepository.findByName(skillName)
                        .orElseGet(
                                () -> skillRepository.save(Skill.builder().name(skillName).build())
                        ) ;
                student.getSkills().add(skill) ;
            }
        }
        Student saveStudent = studentRepository.save(student) ;
        return StudentMapper.mapToResponse(saveStudent) ;
    }

    @Override
    public StudentProfileResponseDto uploadResume(MultipartFile file) {
        Long currentUserId = SecurityUtility.getCurrentUser().getUser().getUserId();
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = user.getStudent() ;
        if(student == null) {
            throw new ResourceNotFoundException(
                    "Student profile not found. Please create one first"
            ) ;
        }

        String filePath = fileStorageService.storeFile(file);
        student.setResumeUrl(filePath);
        Student saveStudent = studentRepository.save(student);

        return StudentMapper.mapToResponse(saveStudent) ;
    }
}
