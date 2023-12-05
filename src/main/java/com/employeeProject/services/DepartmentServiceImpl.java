package com.employeeProject.services;

import com.employeeProject.entity.Employee;
import com.employeeProject.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class DepartmentServiceImpl implements IDepartmentService {

    IEmployeeService employeeService;

    public DepartmentServiceImpl(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryOfDepartment(Integer departmentId) {
        Double maxSalary = getAllEmployees(departmentId).stream()
                .map(e -> e.getSalary())
                .max(Comparator.naturalOrder())
                .orElseThrow(() -> new EmployeeNotFoundException("DepartmentId is uncorrected"));

        return getAllEmployees(departmentId).stream()
                .filter(e -> e.getSalary() == maxSalary)
                .findFirst().
                get();

    }

    @Override
    public Employee minSalaryOfDepartment(Integer departmentId) {
        Double minSalary = getAllEmployees(departmentId).stream()
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new EmployeeNotFoundException("DepartmentId is uncorrected"));

        return getAllEmployees(departmentId).stream()
                .filter(e -> e.getSalary() == minSalary)
                .findFirst().
                get();
    }

    @Override
    public List<Employee> getAllEmployees(Integer departmentId) {
        if (departmentId != null) {
            List<Employee> employeesOfDepartment = employeeService.getAllEmployees().stream()
                    .filter(e -> e.getDepartmentId() == departmentId)
                    .toList();
            if (employeesOfDepartment.isEmpty()) {
                throw new EmployeeNotFoundException("DepartmentId is uncorrected");
            }
            return employeesOfDepartment;
        } else {
            return employeeService.getAllEmployees();
        }
    }
}
