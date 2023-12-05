package com.employeeProject.services;

import com.employeeProject.entity.Employee;

import java.util.List;

public interface DepartmentService {
    Employee maxSalaryOfDepartment(Integer departmentId);

    Employee mimSalaryOfDepartment(Integer departmentId);

    List<Employee> allEmployees(Integer departmentId);
}
