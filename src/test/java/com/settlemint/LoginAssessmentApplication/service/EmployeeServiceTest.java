package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.model.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void saveEmployee() {
        Manager m1=Manager.builder().id(12L).name("Lawrence").teams("HR").build();
        Employee e1=Employee.builder()
                .name("robot")
                .email("robo@gmail.com")
                .department("robot")
                .technology("Machine language")
                .designation("Senior Software Engineer")
                .reportingManager(m1)
                .build();
        Employee saved= employeeService.saveEmployee(e1);
        assertEquals("robo@gmail.com",employeeService.fetchEmployeeById(saved.getId()).get().getEmail());
        employeeService.deleteEmployeeById(saved.getId());
    }

    @Test
    void shouldNotAllowToPersistNullManager() {
        Employee e1=Employee.builder()
                .name("robot")
                .email("blgsathiyan@gmail.com")
                .department("IT")
                .technology("Java")
                .designation("Senior Software Engineer")
                .build();
        assertThrows(DataIntegrityViolationException.class, () -> {
            employeeService.saveEmployee(e1);
        });

    }

    @Test
    void fetchEmployeeList() {
        Manager m1=Manager.builder().id(12L).name("Lawrence").teams("HR").build();
        Employee e1=Employee.builder()
                .name("robot")
                .email("robo@gmail.com")
                .department("robot")
                .technology("Machine language")
                .designation("Senior Software Engineer")
                .reportingManager(m1)
                .build();
        Employee saved= employeeService.saveEmployee(e1);
        List<Employee> elist=employeeService.fetchEmployeeList();
        System.out.println(elist);
        assertNotNull(elist);
        employeeService.deleteEmployeeById(saved.getId());
    }

    @Test
    void updateEmployeeById() {
        Manager m1=Manager.builder().id(12L).name("Lawrence").teams("HR").build();
        Employee e1=Employee.builder()
                .name("robot")
                .email("robo@gmail.com")
                .department("robot")
                .technology("Machine language")
                .designation("Senior Software Engineer")
                .reportingManager(m1)
                .build();
        Employee saved= employeeService.saveEmployee(e1);
        Employee updatedEmployee=Employee.builder()
                .name("root")
                .email("robo@gmail.com")
                .department("robot")
                .technology("Machine language")
                .designation("System")
                .reportingManager(m1)
                .build();
        employeeService.updateEmployeeById(updatedEmployee,saved.getId());
        assertEquals("System",employeeService.fetchEmployeeById(saved.getId()).get().getDesignation());
        employeeService.deleteEmployeeById(saved.getId());
    }

    @Test
    public void deleteEmployeeTest()
    {
        Manager m1=Manager.builder().id(12L).name("Lawrence").teams("HR").build();
        Employee e1=Employee.builder()
                .name("robot001")
                .email("robo001@gmail.com")
                .department("robot")
                .technology("Machine language")
                .designation("Senior Software Engineer")
                .reportingManager(m1)
                .build();
        Employee saved=employeeService.saveEmployee(e1);
        System.out.println(employeeService.fetchEmployeeList());
        assertEquals(saved.getId()+""+" Id got deleted",employeeService.deleteEmployeeById(saved.getId()));
    }


}