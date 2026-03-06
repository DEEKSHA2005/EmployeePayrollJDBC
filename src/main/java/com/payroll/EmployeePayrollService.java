package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class EmployeePayrollService {

    public Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "12345678";

        Connection connection = DriverManager.getConnection(url, username, password);

        System.out.println("Database Connected Successfully");

        return connection;
    }

    public void readEmployeeData() {

        String query = "SELECT * FROM employee_payroll";

        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");

                System.out.println(id + " | " + name + " | " + salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}