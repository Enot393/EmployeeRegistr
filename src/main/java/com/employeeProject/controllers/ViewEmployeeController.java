package com.employeeProject.controllers;

import com.employeeProject.services.EmployeeService;
import com.employeeProject.services.EmployeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/view")
public class ViewEmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    public ViewEmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping(path = "/all")
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeServiceImpl.getAllEmployees());
        return "view";
    }

    @GetMapping(path = "search")
    public String search(Model model, @RequestParam(value = "fullName") String fullName) {
        model.addAttribute("employee", employeeServiceImpl.searchEmployee(fullName));
        return "employee";
    }

    @GetMapping(path = "/{id}")
    public String show(@PathVariable("fullName") String fullName, Model model) {
        model.addAttribute("employee", employeeServiceImpl.searchEmployee(fullName));
        return "employee";
    }

}
