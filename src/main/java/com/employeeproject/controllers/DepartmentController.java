package com.employeeproject.controllers;

import com.employeeproject.entity.Employee;
import com.employeeproject.services.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalaryOfDepartment(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getMaxSalaryOfDepartment(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee mimSalaryOfDepartment(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getMinSalaryOfDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public List<Employee> allEmployees(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getAllEmployeesOfDepartment(departmentId);
    }
}
