package com.settlemint.LoginAssessmentApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeLoginDTO {
    private String username;

    private String password;

    private int otp;
}
