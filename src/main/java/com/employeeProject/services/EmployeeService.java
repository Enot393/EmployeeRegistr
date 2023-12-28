package com.employeeProject.services;

import com.employeeProject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, Integer department, Double salary);

    Employee removeEmployee(String fullName);

    Employee searchEmployee(String fullName);
    List<Employee> getAllEmployees();
    Employee changeEmployeeFields(Integer id, String field, String newValue);



}
