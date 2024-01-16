package com.employeeproject.controllers;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.DepartmentNotFoundException;
import com.employeeproject.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

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
    public Employee minSalaryOfDepartment(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getMinSalaryOfDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public List<Employee> allEmployees(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return departmentService.getAllEmployeesOfDepartment(departmentId);
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public String handleExceptionsOfDepartmentController(DepartmentNotFoundException e) {
        return e.getOutputMessage();
    }
}
