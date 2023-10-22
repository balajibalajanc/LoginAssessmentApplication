package com.settlemint.LoginAssessmentApplication.controller;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.service.DefaultEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private DefaultEmployeeService employeeService;

    public RegistrationController(DefaultEmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    @ModelAttribute("employee")
    public EmployeeRegisteredDTO userRegistrationDto() {
        return new EmployeeRegisteredDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("employee")
                                          EmployeeRegisteredDTO registrationDto) {
        employeeService.save(registrationDto);
        return "redirect:/login";
    }
}
