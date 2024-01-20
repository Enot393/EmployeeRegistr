package com.employeeproject.exceptions.employeeexceptions;

import com.employeeproject.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeAlreadyAddedException extends EmployeeProjectException {

    private final Employee employee;

    public EmployeeAlreadyAddedException(String message, Employee employee) {
        super(message);
        this.employee = employee;
    }

    @Override
    public String getOutputMessage() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>Employee</b><br><br>
                %s<br><br>
                <b>already added!</b>
                """.formatted(employee);
    }
}