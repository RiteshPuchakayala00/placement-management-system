package com.pms.repository;

import com.pms.entity.Student;
import com.pms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student , Long> {
    Optional<Student> findByUser(User user);

    boolean existsByUser(User user) ;

    boolean existsByRollNo(String rollNo) ;
}
