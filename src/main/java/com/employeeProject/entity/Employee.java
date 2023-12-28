package com.employeeProject.entity;

import java.text.NumberFormat;
import java.util.Objects;

public class Employee {
    private String fullName;
    private int departmentId;
    private double salary;
    private final Integer id;
    private static Integer counter = 0;

    public Employee(String fullName, int departmentId, double salary) {
        this.fullName = fullName;
        this.departmentId = departmentId;
        this.salary = salary;
        this.id = counter++;
        // TODO: 19.12.2023 написать бутафорскую базу данных
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public double getSalary() {
        return salary;
    }

    public Integer getId() {
        return id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return ("ID+%s: %s, dept.№ %s, salary %s").formatted(id, fullName, departmentId, nf.format(salary));
    }
}
