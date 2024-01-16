package com.employeeproject.controllers;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.*;
import com.employeeproject.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "lastName") String lastName,
                                @RequestParam(value = "department") Integer department,
                                @RequestParam(value = "salary") Double salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/search")
    public Employee searchEmployee(@RequestParam(value = "firstName") String firstName,
                                   @RequestParam(value = "lastName") String lastName) {
        return employeeService.searchEmployee(firstName, lastName);
    }

    @ExceptionHandler(EmployeeProjectException.class)
    public String handleExceptionsOfProject(EmployeeProjectException e) {
        return e.getOutputMessage();
    }
}
