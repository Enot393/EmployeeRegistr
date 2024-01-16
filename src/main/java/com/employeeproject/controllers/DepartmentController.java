package com.employeeproject.controllers;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.DepartmentNotFoundException;
import com.employeeproject.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}/salary/max")
    public String getMaxSalaryOfDepartment(@PathVariable Integer departmentId) {
        return departmentService.getMaxSalaryOfDepartment(departmentId);
    }

    @GetMapping(path = "/{id}/salary/min")
    public String getMinSalaryOfDepartment(@PathVariable Integer departmentId) {
        return departmentService.getMinSalaryOfDepartment(departmentId);
    }

    @GetMapping(path = "{id}/salary/sum")
    public String getSumOfSalaryOfDepartment(@PathVariable Integer departmentId) {
        return departmentService.getSumOfSalaryOfDepartment(departmentId);
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> getAllEmployeesOfDepartment(@PathVariable Integer departmentId) {
        return departmentService.getEmployeesOfDepartment(departmentId);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> allEmployeesWithGrouping() {
        return departmentService.getEmployeesWithGrouping();
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public String handleExceptionsOfDepartmentController(DepartmentNotFoundException e) {
        return e.getOutputMessage();
    }
}
