package com.employeeproject.services;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.employeeexceptions.EmployeeAlreadyAddedException;
import com.employeeproject.exceptions.employeeexceptions.EmployeeNotFoundException;
import com.employeeproject.exceptions.employeeexceptions.StorageIsFullException;
import com.employeeproject.exceptions.employeeexceptions.UncorrectedInputNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private final Map<String, Employee> employeeBook = new HashMap<>();
    EmployeeServiceImpl out = new EmployeeServiceImpl(employeeBook);

    public static Stream<Arguments> provide_positive_params_for_tests() {
        // given
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 1.0),
                Arguments.of("ivan", "ivanov", 1, 1.0),
                Arguments.of("iVaN", "iVaNoV", 1, 1.0),
                Arguments.of("IVAN", "IVANOV", 1, 1.0),
                Arguments.of("pYfBmlItEDvJo", "PuRcMLurVjGBhoJn", 1, 1.0));
    }

    @ParameterizedTest
    @MethodSource("provide_positive_params_for_tests")
    public void add_employee_test_with_positive_params(String firstName, String lastName, int departmentId, double salary) {

        // given
        Employee expectedEmployee = new Employee(firstName, lastName, departmentId, salary);
        int employeeBookSizeBeforeAdding = employeeBook.size();

        // invoking
        Employee actualEmployee = out.addEmployee(firstName, lastName, departmentId, salary);
        int employeeBookSizeAfterAdding = employeeBook.size();

        // assertion
        assertEquals(employeeBookSizeBeforeAdding, employeeBookSizeAfterAdding - 1);
        assertNotEquals(null, actualEmployee);
        assertTrue(expectedEmployee.equalsIgnoreId(actualEmployee));
    }

    public static Stream<Arguments> provide_negative_params_for_tests() {
        // given
        return Stream.of(
                Arguments.of("Ivan1", "Ivanov", 1, 1.0),
                Arguments.of("Ivan", "Ivanov1", 1, 1.0),
                Arguments.of("", "Ivanov", 1, 1.0),
                Arguments.of("Ivan", "", 1, 1.0),
                Arguments.of("   ", "Ivanov", 1, 1.0),
                Arguments.of("Ivan", "   ", 1, 1.0));
    }

    @ParameterizedTest
    @MethodSource("provide_negative_params_for_tests")
    public void should_exception_at_adding_employee_with_uncorrected_names(
            String firstName, String lastName, int departmentId, double salary) {
        // invoking and assertion
        assertThrows(UncorrectedInputNameException.class,
                () -> out.addEmployee(firstName, lastName, departmentId, salary));
    }

    @Test
    public void should_exception_at_adding_employee_in_full_employee_book() {
        //given
        for (int i = 0; i < 10; i++) {
            out.addEmployee("Ivan", "Ivanov" + (char) ('A' + i), 1, 1.0);
        }
        // invoking and assertion
        assertThrows(StorageIsFullException.class,
                () -> out.addEmployee("Ivan", "IvanovZ", 1, 1.0));
    }

    @Test
    public void should_exception_at_adding_already_added_employee() {
        // given
        out.addEmployee("Ivan", "Ivanov", 1, 1.0);
        // invoking and assertion
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.addEmployee("Ivan", "Ivanov", 1, 1.0));
    }

    @ParameterizedTest
    @MethodSource("provide_positive_params_for_tests")
    public void remove_employee_test_with_positive_params(String firstName, String lastName, int departmentId, double salary) {

        // given
        Employee expectedEmployee = new Employee(firstName, lastName, departmentId, salary);
        out.addEmployee(firstName, lastName, departmentId, salary);
        int employeeBookSizeBeforeRemoving = employeeBook.size();

        // invoking
        Employee actualEmployee = out.removeEmployee(firstName, lastName);
        int employeeBookSizeAfterRemoving = employeeBook.size();

        // assertion
        assertEquals(employeeBookSizeBeforeRemoving, employeeBookSizeAfterRemoving + 1);
        assertNotEquals(null, actualEmployee);
        assertTrue(expectedEmployee.equalsIgnoreId(actualEmployee));
    }

    @Test
    public void should_exception_at_removing_employee_with_another_names() {

        // given
        out.addEmployee("Ivan", "Ivanov", 1, 1.0);

        // invoking and assertion
        assertThrows(EmployeeNotFoundException.class,
                () -> out.removeEmployee("Petr", "Petrov"));
    }

    @ParameterizedTest
    @MethodSource("provide_positive_params_for_tests")
    public void search_employee_test_with_positive_params(String firstName, String lastName, int departmentId, double salary) {

        // given
        Employee expectedEmployee = out.addEmployee(firstName, lastName, departmentId, salary);
        int employeeBookSizeBeforeSearching = employeeBook.size();

        // invoking
        Employee actualEmployee = out.searchEmployee(firstName, lastName);
        int employeeBookSizeAfterSearching = employeeBook.size();

        // assertion
        assertEquals(employeeBookSizeBeforeSearching, employeeBookSizeAfterSearching);
        assertNotEquals(null, actualEmployee);
        assertEquals(actualEmployee, expectedEmployee);
    }

    @Test
    public void should_exception_at_searching_employee_with_another_names() {
        // given
        out.addEmployee("Ivan", "Ivanov", 1, 1.0);

        // invoking and assertion
        assertThrows(EmployeeNotFoundException.class,
                () -> out.searchEmployee("Petr", "Petrov"));
    }
}

