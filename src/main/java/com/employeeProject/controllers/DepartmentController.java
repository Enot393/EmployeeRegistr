package com.employeeProject.controllers;

import com.employeeProject.entity.Employee;
import com.employeeProject.services.DepartmentService;
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
    public Employee maxSalaryOfDepartment(@RequestParam(value = "departmentId") Integer departmentId) {
        return departmentService.maxSalaryOfDepartment(1);
    }

    @GetMapping(path = "/min-salary")
    public Employee mimSalaryOfDepartment(@RequestParam(value = "departmentId") Integer departmentId) {
        return departmentService.maxSalaryOfDepartment(1);
    }

    @GetMapping(path = "/all")
    public List<Employee> allEmployees(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
//        return departmentService.maxSalaryOfDepartment(1);
        return null;
    }
}
