package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeePayrollService {

    public Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "12345678";

        Connection connection = DriverManager.getConnection(url, username, password);

        System.out.println("Database Connected Successfully");

        return connection;
    }
}