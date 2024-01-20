package com.employeeproject.controllers;

import com.employeeproject.exceptions.employeeexceptions.*;
import com.employeeproject.services.EmployeeService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.employeeproject.controllers.ExceptionMessagesConstant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class EmployeeControllerExceptionsHandlerTest {

    private final EmployeeService employeeServiceMock = mock(EmployeeService.class);
    private final EmployeeController out = new EmployeeController(employeeServiceMock);


    public static Stream<Arguments> provide_params_for_exceptions_test() {
        // given
        return Stream.of(
                Arguments.of(EMPLOYEE_ALREADY_ADDED_EXCEPTION_MESSAGE(),
                        new EmployeeAlreadyAddedException("random string", TEST_EMPLOYEE)),

                Arguments.of(EMPLOYEE_NOT_FOUND_EXCEPTION_MESSAGE(),
                        new EmployeeNotFoundException("random string", TEST_EMPLOYEE.getFirstName())),

                Arguments.of(STORAGE_IS_FULL_EXCEPTION_MESSAGE,
                        new StorageIsFullException("random string")),

                Arguments.of(UNCORRECTED_INPUT_NAME_EXCEPTION_MESSAGE(),
                        new UncorrectedInputNameException("random string", TEST_EMPLOYEE.getFirstName()))
        );
    }

    @ParameterizedTest
    @MethodSource("provide_params_for_exceptions_test")
    public void employee_already_added_controller_exception_test(String expectedMessage, EmployeeProjectException exception) {
        // invoking
        String actualMessage = out.handleExceptionsOfEmployeeController(exception);
        //assertion
        assertEquals(expectedMessage, actualMessage);
    }

}
