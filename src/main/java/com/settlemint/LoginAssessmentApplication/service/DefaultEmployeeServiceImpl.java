package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.dto.EmployeeRegisteredDTO;
import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.model.Manager;
import com.settlemint.LoginAssessmentApplication.model.Role;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.repository.ManagerRepository;
import com.settlemint.LoginAssessmentApplication.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class DefaultEmployeeServiceImpl implements DefaultEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ManagerRepository managerRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Employee save(EmployeeRegisteredDTO employeeRegisteredDTO) {
        Role role = roleRepository.findByRole("USER");
        Manager manager=managerRepository.findById(employeeRegisteredDTO.getManagerid());
        Employee employee=new Employee();
        employee.setEmail(employeeRegisteredDTO.getEmail_id());
        employee.setName(employeeRegisteredDTO.getName());
        employee.setPassword(passwordEncoder.encode(employeeRegisteredDTO.getPassword()));
        employee.setRole(role);
        employee.setDepartment(employeeRegisteredDTO.getDepartment());
        employee.setDesignation(employeeRegisteredDTO.getDesignation());
        employee.setTechnology(employeeRegisteredDTO.getTechnology());
        employee.setReportingManager(manager);
        return employeeRepository.save(employee);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee=employeeRepository.findByEmail(email);
        //System.out.println("Inside loadUserByUsername");
        if(employee == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(employee.getEmail(), employee.getPassword(), mapRolesToAuthorities(employee.getRoles()));
        }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
