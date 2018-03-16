package com.tax.calculator.domain;

import org.springframework.stereotype.Component;

@Component("Salary")
public class Salary {
    public Salary() {}

    public Salary(Double salary, String year) {
        this.salary = salary;
        this.year = year;
    }

    public Double salary;

    public String year;

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
