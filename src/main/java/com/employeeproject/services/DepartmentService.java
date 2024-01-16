package com.employeeproject.services;

import com.employeeproject.entity.Employee;

import java.util.List;

public interface DepartmentService {
    Employee getMaxSalaryOfDepartment(Integer departmentId);

    Employee getMinSalaryOfDepartment(Integer departmentId);

    List<Employee> getAllEmployeesOfDepartment(Integer departmentId);
}
