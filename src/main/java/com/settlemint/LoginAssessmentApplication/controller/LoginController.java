package com.settlemint.LoginAssessmentApplication.controller;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeLoginDTO;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.service.DefaultEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private DefaultEmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @ModelAttribute("employee")
    public EmployeeLoginDTO employeeLoginDTO() {
        return new EmployeeLoginDTO();
    }

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public void loginUser(@ModelAttribute("employee") EmployeeLoginDTO employeeLoginDTO) {
        employeeService.loadUserByUsername(employeeLoginDTO.getUsername());
    }

}
