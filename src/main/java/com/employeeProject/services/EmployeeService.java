package com.employeeProject.services;

import com.employeeProject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String fullName, Integer department, Double salary);

    Employee removeEmployee(String fullName);

    Employee searchEmployee(String fullName);
    List<Employee> getAllEmployees();
    Employee changeEmployeeFields(Integer id, String field, String newValue);
    // TODO: 19.12.2023 добавить toChangeEmployee() в котором можно 1.перевести сотрудника в другой отдел 2.переименовать сотрудника  3.проиндексировать ему зп



}
