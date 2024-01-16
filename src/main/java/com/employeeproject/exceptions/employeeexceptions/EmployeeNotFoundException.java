package com.employeeproject.exceptions.employeeexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends EmployeeProjectException {
    private final String name;

    public EmployeeNotFoundException(String message, String name) {
        super(message);
        this.name = name;
    }

    @Override
    public String getOutputMessage() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>Employees with name</b><br><br>
                 %s<br><br>
                <b>does not exist!</b>
                """.formatted(name);
    }
}
