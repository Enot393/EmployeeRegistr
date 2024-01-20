package com.employeeproject.services;

import com.employeeproject.entity.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    String getMaxSalaryOfDepartment(Integer departmentId);

    String getMinSalaryOfDepartment(Integer departmentId);

    String getSumOfSalaryOfDepartment(Integer departmentId);

    List<Employee> getEmployeesOfDepartment(Integer departmentId);

    Map<Integer, List<Employee>> getAllEmployeesWithGrouping();
}
