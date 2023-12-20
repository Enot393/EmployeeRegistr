package com.employeeProject.controllers;

import com.employeeProject.entity.Employee;
import com.employeeProject.services.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(value = "fulltName") String fullName,
                                @RequestParam(value = "department") Integer department,
                                @RequestParam(value = "salary") Double salary) {
        return employeeService.addEmployee(fullName, department, salary);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(value = "firstName") String fullName) {
        return employeeService.removeEmployee(fullName);
    }

    @GetMapping(path = "/search")
    public Employee searchEmployee(@RequestParam(value = "firstName") String fullName) {
        return employeeService.searchEmployee(fullName);
    }

    @GetMapping(path = "/change")
    public Employee changeField(@RequestParam(value = "id") Integer id,
                                @RequestParam(value = "field") String field,
                                @RequestParam(value = "newValue") String newValue) {
        return employeeService.changeEmployeeFields(id, field, newValue);
    }

    @GetMapping(path = "/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
