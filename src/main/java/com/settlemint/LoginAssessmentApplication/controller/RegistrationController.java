package com.settlemint.LoginAssessmentApplication.controller;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeForogotDTO;
import com.settlemint.LoginAssessmentApplication.dto.EmployeeLoginDTO;
import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.repository.TokenRepository;
import com.settlemint.LoginAssessmentApplication.service.DefaultEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private DefaultEmployeeService employeeService;



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
