package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.model.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;

public interface DefaultEmployeeService extends UserDetailsService {

    Employee save(EmployeeRegisteredDTO employeeRegisteredDTO);

    String sendEmail(Employee employee);

    boolean hasExipred(LocalDateTime expiryDateTime);
}
