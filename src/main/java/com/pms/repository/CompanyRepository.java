package com.pms.repository;

import com.pms.entity.Company;
import com.pms.enums.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByApprovalStatus(ApprovalStatus status) ;
}
