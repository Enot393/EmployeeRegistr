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

    public EmployeeServiceImpl(Map<Integer, Employee> employeeBook) {
        this.employeeBook = employeeBook;
    }

    @Override
    public Employee addEmployee(String fullName, Integer department, Double salary) {
        int employeeBookSizeLimit = 10;
        if (employeeBook.size() >= employeeBookSizeLimit) {
            throw new EmployeeStorageIsFullException("StorageIsFull");
        }
        // Сначала допустим, что у нас нет тезок и под одним сочетанием firstName+lastName находится только один экземпляр
        // TODO: 19.12.2023 Реализовать возвращение коллекции сотрудников, на случай, если там имеются тезки
        if (employeeBook.keySet().stream()
                .anyMatch(e -> (employeeBook.get(e).getFullName()).equals(fullName))) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
        } else {
            Employee employee = new Employee(fullName, department, salary);
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
    public Employee removeEmployee(String fullName) {
        return employeeBook.remove(employeeBook.keySet().stream()
                .filter(e -> (employeeBook.get(e).getFullName()).equals(fullName))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + fullName + " isn't found")));
        /*if (employeeBook.containsKey(firstName + lastName)) {
             return employeeBook.remove(firstName + lastName);
        }
        throw new EmployeeNotFoundException("EmployeeNotFound");*/
    }

    @Override
    public Employee searchEmployee(String fullName) {
        return employeeBook.get(employeeBook.keySet().stream()
                .filter(e -> (employeeBook.get(e).getFullName()).equals(fullName))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee " + fullName + " isn't found")));
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
    public Employee changeEmployeeFields(Integer id, String field, String newValue) {
        // Скорее всего изменение полей будет организовано через ввод нового значения в поле ввода на сайте.
        // Так что нет смысла валидировать имена полей, только значения
        Employee employee = employeeBook.get(id);
        switch (field) {
            case "fullName" -> employee.setFullName(newValue);
            case "departmentId" -> {
                try {
                    int departmentId = Integer.parseInt(field);
                    employee.setDepartmentId(departmentId);
                } catch (ClassCastException e) {
                    throw new UncorrectedInputException("Uncorrected inputData " + newValue);
                }
            }
            case "salary" -> {
                try {
                    double salary = Double.parseDouble(field);
                    employee.setSalary(salary);
                } catch (ClassCastException e) {
                    throw new UncorrectedInputException("Uncorrected inputData " + newValue);
                }
            }
        }
        return employeeBook.get(id);
    }
}
