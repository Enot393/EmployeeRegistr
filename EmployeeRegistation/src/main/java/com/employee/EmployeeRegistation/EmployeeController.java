package com.employee.EmployeeRegistation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "add")
    public Employee addEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping(path = "remove")
    public Employee removeEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }


    @GetMapping(path = "search")
    public Employee searchEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.searchEmployee(firstName, lastName);
    }
}
