package com.pms.repository;

import com.pms.entity.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordinatorRepository extends JpaRepository<Coordinator , Long> {
}
