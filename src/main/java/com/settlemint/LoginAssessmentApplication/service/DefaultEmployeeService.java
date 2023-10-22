package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.model.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface DefaultEmployeeService extends UserDetailsService {

    Employee save(EmployeeRegisteredDTO employeeRegisteredDTO);
}
