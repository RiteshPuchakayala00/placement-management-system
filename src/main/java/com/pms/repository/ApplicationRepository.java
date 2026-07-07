package com.pms.repository;

import com.pms.entity.Application;
import com.pms.entity.Opportunity;
import com.pms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application , Long> {
    boolean existsByStudentAndOpportunity(Student student, Opportunity opportunity);
}
