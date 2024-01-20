package com.employeeproject.services;

import com.employeeproject.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    protected static final List<Employee> ALL_EMPLOYEES_TEST_LIST = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 1, 10000.0),
            new Employee("Petr", "Petrov", 1, 20000.0),
            new Employee("Vasiliy", "Vasiliev", 1, 30000.0),
            new Employee("Aleksandr", "Aleksandrov", 2, 40000.0),
            new Employee("Andrey", "Andreev", 2, 50000.0),
            new Employee("Anton", "Antonov", 2, 60000.0),
            new Employee("Maxim", "Maximov", 3, 70000.0),
            new Employee("Roman", "Romanov", 3, 80000.0),
            new Employee("Alexey", "Alexeev", 3, 90000.0),
            new Employee("Nickolay", "Nickolaev", 4, 100_000.0),
            new Employee("Stas", "Stasov", 4, 110_000.0),
            new Employee("Timofey", "Timofeev", 4, 120_000.0)
    ));

    protected static final List<Employee> EMPLOYEES_OF_FIRST_DEPARTMENT = new ArrayList<>(ALL_EMPLOYEES_TEST_LIST.subList(0, 3));
    protected static final List<Employee> EMPLOYEES_OF_SECOND_DEPARTMENT = new ArrayList<>(ALL_EMPLOYEES_TEST_LIST.subList(3, 6));
    protected static final List<Employee> EMPLOYEES_OF_THIRD_DEPARTMENT = new ArrayList<>(ALL_EMPLOYEES_TEST_LIST.subList(6, 9));
    protected static final List<Employee> EMPLOYEES_OF_FOURTH_DEPARTMENT = new ArrayList<>(ALL_EMPLOYEES_TEST_LIST.subList(9, 12));

    protected static final double SUM_OF_SALARY_OF_FIRST_DEPARTMENT = 60000.0;
    protected static final double SUM_OF_SALARY_OF_SECOND_DEPARTMENT = 150_000.0;
    protected static final double SUM_OF_SALARY_OF_THIRD_DEPARTMENT = 240_000.0;
    protected static final double SUM_OF_SALARY_OF_FOURTH_DEPARTMENT = 330_000.0;
}
