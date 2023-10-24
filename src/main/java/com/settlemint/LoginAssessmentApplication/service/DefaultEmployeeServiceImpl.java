package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.model.Manager;
import com.settlemint.LoginAssessmentApplication.model.PasswordResetToken;
import com.settlemint.LoginAssessmentApplication.model.Role;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.repository.ManagerRepository;
import com.settlemint.LoginAssessmentApplication.repository.RoleRepository;

import com.settlemint.LoginAssessmentApplication.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class DefaultEmployeeServiceImpl implements DefaultEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Value("${service.api.google.username}")
    private String gusername;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public Employee save(EmployeeRegisteredDTO employeeRegisteredDTO) {
        Role role = roleRepository.findByRole("USER");
        Manager manager=managerRepository.findById(employeeRegisteredDTO.getManagerid());
        Employee employee=new Employee();
        employee.setEmail(employeeRegisteredDTO.getEmail_id());
        employee.setName(employeeRegisteredDTO.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeRegisteredDTO.getPassword()));
        employee.setRole(role);
        employee.setDepartment(employeeRegisteredDTO.getDepartment());
        employee.setDesignation(employeeRegisteredDTO.getDesignation());
        employee.setTechnology(employeeRegisteredDTO.getTechnology());
        employee.setReportingManager(manager);
        return employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee=employeeRepository.findByName(username);
        //System.out.println("Inside loadUserByUsername");
        if(employee == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), mapRolesToAuthorities(employee.getRoles()));
        }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    public String sendEmail(Employee employee) {
        try {
            String resetLink = generateResetToken(employee);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(gusername);
            msg.setTo(employee.getEmail());

            msg.setSubject("Assessment task ");
            msg.setText("Hello \n\n" + "Click to Reset your Password :" + resetLink + ". \n\n"
                    + "Regards \n" + "Balaji Balasurbamani -task owner");

            javaMailSender.send(msg);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
    public String generateResetToken(Employee employee) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setEmployee(employee);
        resetToken.setToken(uuid.toString());
        resetToken.setExpiryDateTime(expiryDateTime);
        PasswordResetToken token = tokenRepository.save(resetToken);
        if (token != null) {
            String endpointUrl = "http://localhost:8080/resetPassword";
            return endpointUrl + "/" + resetToken.getToken();
        }
        return "";
    }


    public boolean hasExipred(LocalDateTime expiryDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return expiryDateTime.isAfter(currentDateTime);
    }
}
