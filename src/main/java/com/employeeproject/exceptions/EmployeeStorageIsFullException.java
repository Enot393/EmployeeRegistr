package com.employeeproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends EmployeeProjectException {

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }

    @Override
    public String getOutputMessage() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>Storage is full. You must delete someone before adding</b>
                """;
    }
}
