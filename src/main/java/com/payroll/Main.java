package com.payroll;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try {

            EmployeePayrollService service = new EmployeePayrollService();

            Connection connection = service.getConnection();

            if (connection != null) {
                System.out.println("Connection Test Successful");
            } else {
                System.out.println("Connection Test Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}