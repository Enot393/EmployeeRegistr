package com.employeeproject.exceptions;

public class DepartmentNotFoundException extends RuntimeException {

    private final int departmentId;

    public DepartmentNotFoundException(String message, int departmentId) {
        super(message);
        this.departmentId = departmentId;
    }

    public String getOutputMessage() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>Department with this id dies not exist! Your input id:</b><br><br>
                 %s<br><br>
                <b>already added!</b>
                """.formatted(departmentId);
    }
}
