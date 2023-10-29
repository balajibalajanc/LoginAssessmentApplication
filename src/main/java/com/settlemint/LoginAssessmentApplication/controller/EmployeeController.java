package com.settlemint.LoginAssessmentApplication.controller;

import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import com.settlemint.LoginAssessmentApplication.service.DefaultEmployeeService;
import com.settlemint.LoginAssessmentApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@Valid @RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }

    // Read operation
    @GetMapping("/getEmployees")
    @ResponseBody
    public List<Employee> fetchEmployeeList()
    {
        System.out.println("inside the mehtod");
        return employeeService.fetchEmployeeList();
    }

    // Update operation
    @PutMapping("/updateemployee/employees/{id}")
    @ResponseBody
    public String updateEmployee(@RequestBody Employee employee,@PathVariable("id") Integer employeeId)
    {
        return employeeService.updateEmployeeById(employee, employeeId);
    }

    // Delete operation
    @DeleteMapping("/deleteemployee/{id}")
    @ResponseBody
    public String deleteDepartmentById(@PathVariable("id")
                                       Integer employeeId)
    {
        employeeService.deleteEmployeeById(
                employeeId);
        return "Deleted Successfully";
    }
}
