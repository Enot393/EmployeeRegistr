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
        return getAllEmployees(departmentId).stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("DepartmentId is uncorrected"));
    }

    @Override
    public Employee minSalaryOfDepartment(Integer departmentId) {
        return getAllEmployees(departmentId).stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("DepartmentId is uncorrected"));
    }

    @Override
    public List<Employee> getAllEmployees(Integer departmentId) {
        if (departmentId == null) {
            return employeeService.getAllEmployees();
        }
         List<Employee> employeesOfDepartment = getEmployeeList(departmentId);
            if (employeesOfDepartment.isEmpty()) {
                throw new EmployeeNotFoundException("DepartmentId is uncorrected");
            }
            return employeesOfDepartment;
    }

    private List<Employee> getEmployeeList(Integer departmentId) {
        return employeeService.getAllEmployees().stream()
                    .filter(e -> e.getDepartmentId() == departmentId)
                    .toList();
    }
}
