package com.settlemint.LoginAssessmentApplication.repository;

import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.model.Manager;
import com.settlemint.LoginAssessmentApplication.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void saveEmployeeTest()
    {
        Manager m1=Manager.builder().name("Lawrence").teams("HR").build();
        Employee e1=Employee.builder()
                .name("balaji")
                .email("balajibalajanci@gmail.com")
                .department("IT")
                .technology("Java")
                .designation("Senior Software Engineer")
                .reportingManager(m1)
                .build();
        employeeRepository.save(e1);
    }
}