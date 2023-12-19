package com.employeeProject.services;

import com.employeeProject.entity.Employee;

import java.util.List;

public interface DepartmentService {
    Employee maxSalaryOfDepartment(Integer departmentId);

    Employee minSalaryOfDepartment(Integer departmentId);

    List<Employee> getAllEmployeesOfDepartment(Integer departmentId);
    void indexingSalaryOfDep(int department, double index);
}
