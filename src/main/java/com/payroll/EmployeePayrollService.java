package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

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

    public void updateEmployeeSalary(String name, double salary) {

        String query = "UPDATE employee_payroll SET salary = " + salary + " WHERE name = '" + name + "'";

        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(query);

            System.out.println("Rows Updated: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeSalaryPrepared(String name, double salary) {

        String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2, name);

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("Rows Updated: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getEmployeeByDate(String startDate) {

        String query = "SELECT * FROM employee_payroll WHERE start_date BETWEEN ? AND CURDATE()";

        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, startDate);

            ResultSet resultSet = preparedStatement.executeQuery();

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

    public void getSalaryStatisticsByGender() {

        String query = "SELECT gender, SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) " +
                "FROM employee_payroll GROUP BY gender";

        try (Connection connection = this.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                String gender = resultSet.getString(1);
                double sum = resultSet.getDouble(2);
                double avg = resultSet.getDouble(3);
                double min = resultSet.getDouble(4);
                double max = resultSet.getDouble(5);
                int count = resultSet.getInt(6);

                System.out.println("Gender: " + gender);
                System.out.println("Total Salary: " + sum);
                System.out.println("Average Salary: " + avg);
                System.out.println("Minimum Salary: " + min);
                System.out.println("Maximum Salary: " + max);
                System.out.println("Employee Count: " + count);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String name, String gender, double salary, String startDate) {

        String query = "INSERT INTO employee_payroll(name, gender, salary, start_date) VALUES (?, ?, ?, ?)";

        try (Connection connection = this.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setString(4, startDate);

            int rowsInserted = preparedStatement.executeUpdate();

            System.out.println("Employee Added: " + rowsInserted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}