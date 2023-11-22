package com.employee.EmployeeRegistation;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employeeBook = new ArrayList<>();
    private final int employeeBookLimit = 10;

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        // EmployeeStorageIsFullException
        // EmployeeAlreadyAddedException
        return null;
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        // EmployeeNotFoundException
        return null;
    }

    @Override
    public Employee searchEmployee(String firstName, String lastName) {
        // EmployeeNotFoundException
        return null;
    }
}
