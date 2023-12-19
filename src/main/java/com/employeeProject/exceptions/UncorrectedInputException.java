package com.employeeProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UncorrectedInputException extends RuntimeException {
    public UncorrectedInputException(String massage) {
        super(massage);
    }
}
