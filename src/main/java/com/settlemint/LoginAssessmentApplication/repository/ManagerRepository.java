package com.settlemint.LoginAssessmentApplication.repository;

import com.settlemint.LoginAssessmentApplication.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {

    Manager findById(long mid);

    @Query("SELECT m FROM Manager m WHERE m.name = :name AND m.teams = :teams")
    List<Manager> findByNameAndTeams(String name, String teams);
}
