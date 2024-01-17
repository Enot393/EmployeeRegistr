package com.employeeproject.services;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.DepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance();

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String getMaxSalaryOfDepartment(Integer departmentId) {
        return nf.format(getEmployeesOfDepartment(departmentId).stream()
                .mapToDouble(Employee::getSalary)
                .max().orElseThrow(() ->
                        new DepartmentNotFoundException("DepartmentId " + departmentId + "is uncorrected", departmentId)));
    }

    @Override
    public String getMinSalaryOfDepartment(Integer departmentId) {
        return nf.format(getEmployeesOfDepartment(departmentId).stream()
                .mapToDouble(Employee::getSalary)
                .min().orElseThrow(() ->
                        new DepartmentNotFoundException("DepartmentId " + departmentId + "is uncorrected", departmentId)));
    }

    @Override
    public String getSumOfSalaryOfDepartment(Integer departmentId) {
        return nf.format(getEmployeesOfDepartment(departmentId).stream()
                .mapToDouble(Employee::getSalary)
                .sum());
    }

    @Override
    public List<Employee> getEmployeesOfDepartment(Integer departmentId) {

        List<Employee> employeesOfDepartment = employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .toList();

        if (employeesOfDepartment.isEmpty()) {
            throw new DepartmentNotFoundException("DepartmentId " + departmentId + "is uncorrected", departmentId);
        }
        return employeesOfDepartment;
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesWithGrouping() {
            return employeeService.getAllEmployees().stream()
                    .collect(Collectors.groupingBy(
                            Employee::getDepartmentId, Collectors.toList()));
    }
}
