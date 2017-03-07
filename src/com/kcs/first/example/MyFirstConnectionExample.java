package com.kcs.first.example;

import java.sql.*;

/**
 * Created by Askew on 3/7/2017.
 */
public class MyFirstConnectionExample {

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kcs", "root", "");

            if (connection != null) {
                System.out.println("Connected.");
                getStudents(connection);
            }

        } catch (SQLException e) {
            System.out.println("Connection to DB failed.");
        }
    }

    private static void getStudents(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()){
                System.out.println("id >> " + resultSet.getInt(1)
                    + " userName >> " + resultSet.getString("name")
                    + " surname >> " + resultSet.getString("surname")
                    + " phone >> " + resultSet.getString("phone")
                    + " email >> " + resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
