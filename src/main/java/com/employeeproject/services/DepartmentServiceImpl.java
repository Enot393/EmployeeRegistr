package com.employeeproject.services;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMaxSalaryOfDepartment(Integer departmentId) {
        return getAllEmployeesOfDepartment(departmentId).stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() ->
                        new DepartmentNotFoundException("DepartmentId " + departmentId + "is uncorrected", departmentId));
    }

    @Override
    public Employee getMinSalaryOfDepartment(Integer departmentId) {
        return getAllEmployeesOfDepartment(departmentId).stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() ->
                        new DepartmentNotFoundException("DepartmentId " + departmentId + "is uncorrected", departmentId));
    }

    @Override
    public List<Employee> getAllEmployeesOfDepartment(Integer departmentId) {
        if (departmentId == null) {
            return employeeService.getAllEmployees();
        }
        List<Employee> employeesOfDepartment = getEmployeeList(departmentId);
        if (employeesOfDepartment.isEmpty()) {
            throw new DepartmentNotFoundException("DepartmentId " + departmentId + "is uncorrected", departmentId);
        }
        return employeesOfDepartment;
    }

    private List<Employee> getEmployeeList(Integer departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .toList();
    }
}
