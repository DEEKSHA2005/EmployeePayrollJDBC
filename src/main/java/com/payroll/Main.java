package com.payroll;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        service.updateEmployeeSalaryPrepared("Bill", 3500000);

        service.readEmployeeData();
    }
}