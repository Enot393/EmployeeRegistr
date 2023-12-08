package com.employeeProject.services;

import com.employeeProject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, Integer department, Double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee searchEmployee(String firstName, String lastName);
    List<Employee> getAllEmployees();
}
