package com.pms.repository;

import com.pms.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application , Long> {
}
