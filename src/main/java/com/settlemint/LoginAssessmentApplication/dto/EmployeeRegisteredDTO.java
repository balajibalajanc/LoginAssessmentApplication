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
public class EmployeeRegisteredDTO {

    private String name;

    private String email_id;

    private String password;
    private String department;
    private String designation;
    private String technology;
    private long managerid;
    private String role;
}
