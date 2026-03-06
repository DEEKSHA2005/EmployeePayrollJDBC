package com.payroll;

public class Main {

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        service.updateEmployeeSalary("Bill", 3000000);

        service.readEmployeeData();

    }
}