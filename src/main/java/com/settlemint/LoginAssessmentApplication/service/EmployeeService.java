package com.settlemint.LoginAssessmentApplication.service;

import com.settlemint.LoginAssessmentApplication.model.Employee;
import com.settlemint.LoginAssessmentApplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
@Autowired
private EmployeeRepository employeeRepository;

 public Employee saveEmployee(Employee employee)
 {

  return employeeRepository.save(employee);
 }

    public Employee fetchEmployeeByEmail(String email)
    {

        return employeeRepository.findByEmail(email);
    }

    public Optional<Employee> fetchEmployeeById(int EmployeeId)
    {

        return employeeRepository.findById(EmployeeId);
    }
 public List<Employee> fetchEmployeeList()
 {
  return employeeRepository.findAll();
 }

 public String updateEmployeeById(Employee employee,Integer existingemployeeId)
 {
    Optional<Employee> empOptional= employeeRepository.findById(existingemployeeId);
   if(empOptional.isPresent())
   {
       Employee needtoUpdate=empOptional.get();
       needtoUpdate.setEmail(employee.getEmail());
       needtoUpdate.setDesignation(employee.getDesignation());
       needtoUpdate.setPassword(employee.getPassword());
       needtoUpdate.setName(employee.getName());
       needtoUpdate.setRoles(employee.getRoles());
       needtoUpdate.setDepartment(employee.getDepartment());
       needtoUpdate.setTechnology(employee.getTechnology());
       needtoUpdate.setReportingManager(employee.getReportingManager());
    employeeRepository.save(needtoUpdate);
    return existingemployeeId+" "+"got updated";
   }
   return "No Employee with the mentioned Id is found";
 }

    public String deleteEmployeeById(Integer employeeId)
    {
        Optional<Employee> empOptional= employeeRepository.findById(employeeId);
        if(empOptional.isPresent())
        {
            employeeRepository.deleteById(employeeId);
            return employeeId+" "+ "Id got deleted";
        }
        return "No Employee with the mentioned Id is found"+ " "+employeeId;
    }
}
