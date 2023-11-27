package com.employee.EmployeeRegistation;

import com.employee.EmployeeRegistation.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private List<Employee> employeeBook;
    private final int employeeBookLimit = 10;

    public EmployeeServiceImpl(List<Employee> employeeBook) {
        this.employeeBook = employeeBook;
    }
    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employeeBook.size() >= employeeBookLimit) {
            throw new EmployeeStorageIsFullException("ArrayIsFull");
        }
        for (Employee employee : employeeBook) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
            }
        }
        Employee employee = new Employee(firstName, lastName);
        employeeBook.add(employee);
        return employee;
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        for (Employee employee : employeeBook) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                employeeBook.remove(employee);
                return employee;
            }
        }
        throw new EmployeeNotFoundException("EmployeeNotFound");
    }

    @Override
    public Employee searchEmployee(String firstName, String lastName) {
        for (Employee employee : employeeBook) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("EmployeeNotFound");
    }
}
