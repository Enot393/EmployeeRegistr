package com.employeeProject.services;

import com.employeeProject.entity.Employee;
import com.employeeProject.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private Map<String, Employee> employeeBook;
    private final int employeeBookLimit = 10;

    public EmployeeServiceImpl(Map<String, Employee> employeeBook) {
        this.employeeBook = employeeBook;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer department, Double salary) {
        if (employeeBook.size() >= employeeBookLimit) {
            throw new EmployeeStorageIsFullException("StorageIsFull");
        }
        if (employeeBook.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
        } else {
            Employee employee = new Employee(firstName, lastName, department, salary);
            employeeBook.put(firstName + lastName, employee);
            return employee;
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (employeeBook.containsKey(firstName + lastName)) {
             return employeeBook.remove(firstName + lastName);
        }
        throw new EmployeeNotFoundException("EmployeeNotFound");
    }

    @Override
    public Employee searchEmployee(String firstName, String lastName) {
        if (employeeBook.containsKey(firstName + lastName)) {
            return employeeBook.get(firstName + lastName);
        }
        throw new EmployeeNotFoundException("EmployeeNotFound");
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeBook.values());
    }
}
