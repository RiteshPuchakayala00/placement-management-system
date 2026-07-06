package com.pms.repository;

import com.pms.entity.Company;
import com.pms.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpportunityRepository extends JpaRepository<Opportunity , Long> {
    Optional<Opportunity> findByCompany(Company company);
}
