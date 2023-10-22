package com.settlemint.LoginAssessmentApplication.config;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.service.DefaultEmployeeService;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    EmployeeRepository employeeRepository;

    @Autowired
    DefaultEmployeeService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = null;
        System.out.println("debug thailand"+""+authentication);
        if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User  userDetails = (DefaultOAuth2User ) authentication.getPrincipal();
            String username = userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login")+"@gmail.com" ;
            System.out.println("Authentication success Balaji");
            if(employeeRepository.findByEmail(username) == null) {
                EmployeeRegisteredDTO employee = new EmployeeRegisteredDTO();
                employee.setEmail_id(username);
                employee.setName(userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login"));
                employee.setPassword(("Dummy"));
                employee.setRole("USER");
                userService.save(employee);
        }
        }
        System.out.println("debug thailand"+""+httpServletRequest+""+httpServletResponse);
        redirectUrl = "/dashboard";
        new DefaultRedirectStrategy().sendRedirect(httpServletRequest, httpServletResponse, redirectUrl);
    }
}
