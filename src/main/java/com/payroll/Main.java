package com.payroll;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        service.addEmployee("Rahul", "M", 4500000, "2022-06-10");

        service.readEmployeeData();
    }
}