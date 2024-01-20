package com.employeeproject.entity;

import java.text.NumberFormat;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.capitalize;


public class Employee {
    private final String firstName;
    private final String lastName;
    private int departmentId;
    private double salary;
    private final int id;
    private static int countId = 1;

    public Employee(String firstName, String lastName, int departmentId, double salary) {
        this.firstName = capitalize(firstName.toLowerCase());
        this.lastName = capitalize(lastName.toLowerCase());
        this.departmentId = departmentId;
        this.salary = salary;
        this.id = countId;
        countId++;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public double getSalary() {
        return salary;
    }
    public String getFullName() {
        return firstName + lastName;
    }

    // Id - это уникальный идентификатор объекта, так что можно привязать equals и hashCode только лишь к Id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Этот метод нужен, чтобы можно было корректно сравнивать объекты Employee без привязки к Id,
    // ведь на практике двух равных по equals объектов типа Employee не существует
    public boolean equalsIgnoreId(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId && Double.compare(employee.salary, salary) == 0 && firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return "Id%d: %s %s, dept: %d, salary: %s"
                .formatted(id, firstName, lastName, departmentId, nf.format(salary));
    }
}
