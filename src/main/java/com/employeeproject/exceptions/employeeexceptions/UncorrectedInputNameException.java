package com.employeeproject.exceptions.employeeexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UncorrectedInputNameException extends EmployeeProjectException {

    private final String name;
    public UncorrectedInputNameException(String massage, String name) {
        super(massage);
        this.name = name;
    }

    @Override
    public String getOutputMessage() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>You make a mistake in same namefield of employee! Your name:</b><br><br>
                 %s
                """.formatted(name);
    }
}
