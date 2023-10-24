package com.settlemint.LoginAssessmentApplication.repository;

import com.settlemint.LoginAssessmentApplication.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    PasswordResetToken findByToken(String token);

}
