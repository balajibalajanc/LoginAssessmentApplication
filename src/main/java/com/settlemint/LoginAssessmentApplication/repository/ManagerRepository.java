package com.settlemint.LoginAssessmentApplication.repository;

import com.settlemint.LoginAssessmentApplication.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    Manager findById(long mid);
}
