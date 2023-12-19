package com.employeeProject.services;

import com.employeeProject.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, Integer department, Double salary);

    // TODO: 19.12.2023 удалять по id
    Employee removeEmployee(String firstName, String lastName);

    Employee searchEmployee(String firstName, String lastName);
    List<Employee> getAllEmployees();
    void changeEmployeeField(String field, String newValue);
    // TODO: 19.12.2023 добавить toChangeEmployee() в котором можно 1.перевести сотрудника в другой отдел 2.переименовать сотрудника  3.проиндексировать ему зп



}
