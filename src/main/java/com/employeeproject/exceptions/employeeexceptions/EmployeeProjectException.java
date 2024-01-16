package com.employeeproject.exceptions.employeeexceptions;

public abstract class EmployeeProjectException extends RuntimeException {

    public EmployeeProjectException(String message) {
        super(message);
    }

    public abstract String getOutputMessage();
}
