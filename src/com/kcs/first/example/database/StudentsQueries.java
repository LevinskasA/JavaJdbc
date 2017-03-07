package com.kcs.first.example.database;

import com.kcs.first.example.vo.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Askew on 3/7/2017.
 */
public class StudentsQueries {

    private Connection connection;

    public StudentsQueries(Connection connection) {
        this.connection = connection;
    }

    public void getStudents() {
        try {
            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
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

    public List<Student> getStudentsList() {
        List<Student> students = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                Student student = new Student(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return students;
    }

    public void updateStudentName(int studentId, String name) {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE students SET name = '" + name + "' WHERE id = " + studentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // If student with matching (name,surname) exists returns true. Defaults to false if SQLException is thrown.
    public boolean isStudentExist(Student student) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE name = '" + student.getName() + "' AND surname = '" + student.getSurname() + "'");

            if (resultSet.next()) {
                System.out.println("Student '" + student.toString() + "' already exists.");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("isStudentExist SQLException");
            return false;
        }

    }

    public void insertNewStudent(String name, String surname) {
        try {
            //Statement statement = connection.createStatement();
            //statement.execute("INSERT INTO students (name, surname) VALUES ('" + name + "', '" + surname + "');");
            PreparedStatement st = connection.prepareStatement("SELECT * FROM students WHERE name =? AND surname =?");
            st.setString(1, name);
            st.setString(2, surname);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertNewStudent(Student student) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO students (name, surname, phone, email) VALUES ('" + student.getName() + "', '" + student.getSurname() + "', '" + student.getPhone() + "', '" + student.getEmail() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
