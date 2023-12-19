package com.employeeProject.services;

import com.employeeProject.entity.Employee;
import com.employeeProject.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer, Employee> employeeBook;
    private final int employeeBookSizeLimit = 10;

    public EmployeeServiceImpl(Map<Integer, Employee> employeeBook) {
        this.employeeBook = employeeBook;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer department, Double salary) {
        if (employeeBook.size() >= employeeBookSizeLimit) {
            throw new EmployeeStorageIsFullException("StorageIsFull");
        }
        // Сначала допустим, что у нас нет тезок и под одним сочетанием firstName+lastName находится только один экземпляр
        // TODO: 19.12.2023 Реализовать возвращение коллекции сотрудников, на случай, если там имеются тезки
        if (employeeBook.keySet().stream()
                .anyMatch(e -> (employeeBook.get(e).getFirstName() + employeeBook.get(e).getLastName()).equals(firstName + lastName))) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
        } else {
            Employee employee = new Employee(firstName, lastName, department, salary);
            return employeeBook.put(employee.getId(), employee);
        }
        /*if (employeeBook.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
        } else {
            Employee employee = new Employee(firstName, lastName, department, salary);
            return employeeBook.put(employee.getId(), employee);
        }*/
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        return employeeBook.remove(employeeBook.keySet().stream()
                .filter(e -> (employeeBook.get(e).getFirstName() + employeeBook.get(e).getLastName()).equals(firstName + lastName))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with these fields isn't found")));
        /*if (employeeBook.containsKey(firstName + lastName)) {
             return employeeBook.remove(firstName + lastName);
        }
        throw new EmployeeNotFoundException("EmployeeNotFound");*/
    }

    @Override
    public Employee searchEmployee(String firstName, String lastName) {
        return employeeBook.get(employeeBook.keySet().stream()
                .filter(e -> (employeeBook.get(e).getFirstName() + employeeBook.get(e).getLastName()).equals(firstName + lastName))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with these fields isn't found")));

        /*if (employeeBook.containsKey(firstName + lastName)) {
            return employeeBook.get(firstName + lastName);
        }
        throw new EmployeeNotFoundException("EmployeeNotFound");*/
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeBook.values());
    }

    @Override
    public void changeEmployeeField(String field, String newValue) {

    }
}
