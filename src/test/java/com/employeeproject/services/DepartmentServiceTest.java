package com.employeeproject.services;

import com.employeeproject.entity.Employee;
import com.employeeproject.exceptions.DepartmentNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.employeeproject.services.EmployeesConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeServiceMock;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance();
    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    public void initMock() {
        when(employeeServiceMock.getAllEmployees()).thenReturn(ALL_EMPLOYEES_TEST_LIST);
    }

    @AfterEach
    public void countCallGetAllEmployees() {
        verify(employeeServiceMock, only()).getAllEmployees();
    }

    public static Stream<Arguments> provide_departments_params() {
        // given
        return Stream.of(
                Arguments.of(EMPLOYEES_OF_FIRST_DEPARTMENT, 1),
                Arguments.of(EMPLOYEES_OF_SECOND_DEPARTMENT, 2),
                Arguments.of(EMPLOYEES_OF_THIRD_DEPARTMENT, 3),
                Arguments.of(EMPLOYEES_OF_FOURTH_DEPARTMENT, 4));
    }

    @ParameterizedTest
    @MethodSource("provide_departments_params")
    public void get_employee_of_department_positive_test(List<Employee> expectedListOfEmployeesOfDepartment, int departmentId) {
        // invoking
        List<Employee> actualListOfEmployeesOfDepartment = out.getEmployeesOfDepartment(departmentId);
        // assertion
        assertEquals(expectedListOfEmployeesOfDepartment, actualListOfEmployeesOfDepartment);
        verify(employeeServiceMock, times(1)).getAllEmployees();
    }

    @Test
    public void should_exception_when_getting_employees_of_nonexistent_department() {
        assertThrows(DepartmentNotFoundException.class,
                () -> out.getEmployeesOfDepartment(-1));

    }

    @ParameterizedTest
    @MethodSource("provide_departments_params")
    public void get_min_salary_of_department_positive_test(List<Employee> listOfEmployeesOfDepartment, int departmentId) {
        // given
        String expectedMinSalary = nf.format(listOfEmployeesOfDepartment.get(0).getSalary());
        // invoking
        String actualMinSalary = out.getMinSalaryOfDepartment(departmentId);
        // assertion
        assertEquals(expectedMinSalary, actualMinSalary);
    }

    @Test
    public void should_exception_when_departmentId_is_uncorrected_in_get_MIN_salary_method() {
        assertThrows(DepartmentNotFoundException.class,
                () -> out.getMinSalaryOfDepartment(-1));
    }

    @ParameterizedTest
    @MethodSource("provide_departments_params")
    public void get_max_salary_of_department_positive_test(List<Employee> listOfEmployeesOfDepartment, int departmentId) {
        // given
        String actualMaxSalary =
                nf.format(listOfEmployeesOfDepartment.get(listOfEmployeesOfDepartment.size() - 1).getSalary());
        // invoking
        String expectedMaxSalary = out.getMaxSalaryOfDepartment(departmentId);
        // assertion
        assertEquals(expectedMaxSalary, actualMaxSalary);
    }

    @Test
    public void should_exception_when_departmentId_is_uncorrected_in_get_MAX_salary_method() {
        assertThrows(DepartmentNotFoundException.class,
                () -> out.getMaxSalaryOfDepartment(-1));
    }


    public static Stream<Arguments> provide_params_for_sum_of_salaries() {
        // given
        return Stream.of(
                Arguments.of(1, SUM_OF_SALARY_OF_FIRST_DEPARTMENT),
                Arguments.of(2, SUM_OF_SALARY_OF_SECOND_DEPARTMENT),
                Arguments.of(3, SUM_OF_SALARY_OF_THIRD_DEPARTMENT),
                Arguments.of(4, SUM_OF_SALARY_OF_FOURTH_DEPARTMENT));
    }

    @ParameterizedTest
    @MethodSource("provide_params_for_sum_of_salaries")
    public void get_sum_of_salary_of_department_positive_test(int departmentId, double sumOfSalariesOfDepartment) {
        // given
        String expectedSum = nf.format(sumOfSalariesOfDepartment);
        // invoking
        String actualSum = out.getSumOfSalaryOfDepartment(departmentId);
        // assertion
        assertEquals(expectedSum, actualSum);
    }

    @Test
    public void should_exception_when_departmentId_is_uncorrected_in_SUM_of_salaries_method() {
        assertThrows(DepartmentNotFoundException.class,
                () -> out.getSumOfSalaryOfDepartment(-1));
    }

    @Test
    public void get_all_employees_with_grouping_test() {
        // given
        Map<Integer, List<Employee>> expectedMapOfEmployeeWithGrouping = new HashMap<>(Map.of(
                1, EMPLOYEES_OF_FIRST_DEPARTMENT,
                2, EMPLOYEES_OF_SECOND_DEPARTMENT,
                3, EMPLOYEES_OF_THIRD_DEPARTMENT,
                4, EMPLOYEES_OF_FOURTH_DEPARTMENT
        ));
        // invoking
        Map<Integer, List<Employee>> actualMapOfEmployeeWithGrouping = out.getAllEmployeesWithGrouping();

        // assertion
        assertEquals(expectedMapOfEmployeeWithGrouping, actualMapOfEmployeeWithGrouping);
    }

}
