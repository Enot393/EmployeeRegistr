package com.employeeproject.controllers;

import com.employeeproject.entity.Employee;

public class ExceptionMessagesConstant {

    protected static final Employee TEST_EMPLOYEE = new Employee("Ivan", "Ivanov", 1, 1.0);


    protected static String EMPLOYEE_ALREADY_ADDED_EXCEPTION_MESSAGE() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>Employee</b><br><br>
                %s<br><br>
                <b>already added!</b>
                """.formatted(TEST_EMPLOYEE);
    }

    protected static String EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>Employees with name</b><br><br>
                %s<br><br>
                <b>does not exist!</b>
                """.formatted(TEST_EMPLOYEE.getFirstName());
    }

    protected static final String STORAGE_IS_FULL_EXCEPTION_MESSAGE = """
            <h2>Oops!</h2>
            <br>
            <b>Storage is full. You must delete someone before adding</b>
            """;

    protected static String UNCORRECTED_INPUT_NAME_EXCEPTION_MESSAGE() {
        return """
                <h2>Oops!</h2>
                <br>
                <b>You make a mistake in same namefield of employee! Your name:</b><br><br>
                %s
                """.formatted(TEST_EMPLOYEE.getFirstName());
    }
}
