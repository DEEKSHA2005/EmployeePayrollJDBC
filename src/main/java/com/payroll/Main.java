package com.payroll;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        service.getEmployeeByDate("2019-01-01");

    }
}