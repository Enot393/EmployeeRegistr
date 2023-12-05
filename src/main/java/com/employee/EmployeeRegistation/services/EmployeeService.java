package com.employee.EmployeeRegistation.services;

import com.employee.EmployeeRegistation.Employee;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee searchEmployee(String firstName, String lastName);
}
