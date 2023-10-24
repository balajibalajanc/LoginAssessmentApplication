package com.settlemint.LoginAssessmentApplication.controller;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeForogotDTO;
import com.settlemint.LoginAssessmentApplication.dto.EmployeeLoginDTO;
import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.model.PasswordResetToken;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.repository.TokenRepository;
import com.settlemint.LoginAssessmentApplication.service.DefaultEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private DefaultEmployeeService employeeService;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @ModelAttribute("employee")
    public EmployeeLoginDTO employeeLoginDTO() {
        return new EmployeeLoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public void loginUser(@ModelAttribute("employee") EmployeeLoginDTO employeeLoginDTO) {
        employeeService.loadUserByUsername(employeeLoginDTO.getUsername());
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassordProcess(@ModelAttribute EmployeeForogotDTO employeeDTO) {
        String output = "";
        Employee emp = employeeRepository.findByEmail(employeeDTO.getEmail());
        if (emp != null) {
            output = employeeService.sendEmail(emp);
        }
        if (output.equals("success")) {
            return "redirect:/forgotPassword?success";
        }
        return "redirect:/login?error";
    }
    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(@PathVariable String token, Model model) {
        System.out.println(model);
        PasswordResetToken reset = tokenRepository.findByToken(token);
        if (reset != null && employeeService.hasExipred(reset.getExpiryDateTime())) {
            model.addAttribute("email", reset.getEmployee().getEmail());
            return "resetPassword";
        }
        return "redirect:/forgotPassword?error";
    }

    @PostMapping("/resetPassword")
    public String passwordResetProcess(@ModelAttribute EmployeeForogotDTO employeeForogotDTO) {
        Employee employee = employeeRepository.findByEmail(employeeForogotDTO.getEmail());
        if(employee != null) {
            employee.setPassword(passwordEncoder.encode(employeeForogotDTO.getPassword()));
            employeeRepository.save(employee);
        }
        return "redirect:/login";
    }

}
