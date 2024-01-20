package com.employeeproject.exceptions.employeeexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StorageIsFullException extends EmployeeProjectException {

    public StorageIsFullException(String message) {
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
