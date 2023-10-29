package com.settlemint.LoginAssessmentApplication.config;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.model.Manager;
import com.settlemint.LoginAssessmentApplication.model.Role;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.repository.RoleRepository;
import com.settlemint.LoginAssessmentApplication.service.DefaultEmployeeService;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.settlemint.LoginAssessmentApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeService employeeService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = null;

        if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User  userDetails = (DefaultOAuth2User ) authentication.getPrincipal();
            String username = userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login")+"@gmail.com" ;
            if(employeeService.fetchEmployeeByEmail(username) == null) {
                Manager m1=Manager.builder().id(1L).name("Pallavi").teams("Team A").build();
                Role role = roleRepository.findByRole("USER");
                Employee employeeModel= new Employee();
                employeeModel.setName(userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login"));
                employeeModel.setEmail(username);
                employeeModel.setPassword(("Access Token"));
                employeeModel.setDesignation(("Application"));
                employeeModel.setDepartment(("External"));
                employeeModel.setTechnology(("Github"));
                employeeModel.setRole(role);
                employeeModel.setReportingManager(m1);
                employeeService.saveEmployee(employeeModel);
        }
        }

        redirectUrl = "/dashboard";
        new DefaultRedirectStrategy().sendRedirect(httpServletRequest, httpServletResponse, redirectUrl);
    }
}
