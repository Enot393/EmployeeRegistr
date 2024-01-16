package com.employeeproject.services;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.EmployeeAlreadyAddedException;
import com.employeeproject.exceptions.EmployeeNotFoundException;
import com.employeeproject.exceptions.EmployeeStorageIsFullException;
import com.employeeproject.exceptions.UncorrectedInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employeeBook;
    private final int employeeBookLimit = 10;

    public EmployeeServiceImpl(Map<String, Employee> employeeBook) {
        this.employeeBook = employeeBook;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer department, Double salary) {

        if (employeeBook.size() >= employeeBookLimit) {
            throw new EmployeeStorageIsFullException("Storage is full");
        }

        firstName = validateInputName(firstName);
        lastName = validateInputName(lastName);
        Employee employee = employeeBook.get(firstName + lastName);

        if (employee != null) {
            throw new EmployeeAlreadyAddedException("Employee already added: " + employee, employee);
        } else {
            Employee newEmployee = new Employee(firstName, lastName, department, salary);
            employeeBook.put(firstName + lastName, newEmployee);
            return newEmployee;
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        String fullName = validateInputName(firstName) + " " + validateInputName(lastName);
        Employee employee = employeeBook.get(fullName);

        if (employee == null) {
            throw new EmployeeNotFoundException
                    ("Employee named \"" + fullName + "\" isn't found", fullName);
        } else {
            return employeeBook.remove(firstName + lastName);
        }
    }

    @Override
    public Employee searchEmployee(String firstName, String lastName) {

        String fullName = validateInputName(firstName) + " " + validateInputName(lastName);
        Employee employee = employeeBook.get(firstName + lastName);

        if (employee == null) {
            throw new EmployeeNotFoundException
                    ("Employee named \"" + fullName + "\" isn't found", fullName);
        } else {
            return employeeBook.get(firstName + lastName);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeBook.values());
    }

    private String validateInputName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new UncorrectedInputException("Uncorrected input: ", name);
        }
        return capitalize(name.toLowerCase());
    }
}
