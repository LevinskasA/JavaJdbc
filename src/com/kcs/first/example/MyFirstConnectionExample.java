package com.kcs.first.example;

import com.kcs.first.example.database.StudentsQueries;
import com.kcs.first.example.vo.Student;
import com.kcs.utils.JdbcUtils;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Askew on 3/7/2017.
 */
public class MyFirstConnectionExample {

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kcs", "root", "");

            if (connection != null) {
                System.out.println("Connected.");
                StudentsQueries studentsQueries = new StudentsQueries(connection);
                /*
                studentsQueries.getStudents();
                System.out.println("is table exist >> " + JdbcUtils.isTableExist(connection, "students"));
                studentsQueries.updateStudentName(4, "Rokas");
                */
                /*
                Student student = new Student("Juozas", "Baltas", "864545315", "baltajuozis@hotmail.com");
                studentsQueries.insertNewStudent(student);
                */
                String input = "1";
                while (!input.equals("0")) {
                    displayMainMenu();
                    input = userInputMainMenu();
                    switch (input) {
                        case "1":
                            // User inputs new student.
                            userInputInsertStudent(studentsQueries);
                            break;
                        case "4":
                            //displayAllStudents
                            displayAllStudents(studentsQueries);
                            break;
                        case "0":
                            //exits while loop
                            break;
                    }
                }
                System.out.println("Program shut down.");

            }

        } catch (SQLException e) {
            System.out.println("Connection to DB failed.");
        }
    }

    private static void userInputInsertStudent(StudentsQueries studentsQueries){
        System.out.println("Student's name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Student's surname:");
        String surname = scanner.nextLine();
        System.out.println("Student's phone number:");
        String phone = scanner.nextLine();
        System.out.println("Student's email:");
        String email = scanner.nextLine();

        Student student = new Student(name, surname, phone, email);
        // if student doesn't exist
        if (!studentsQueries.isStudentExist(student)){
            studentsQueries.insertNewStudent(student);
        }
    }

    private static void displayAllStudents(StudentsQueries studentsQueries){
        List<Student> students = studentsQueries.getStudentsList();
        System.out.println("|---------------|--------------------|---------------|------------------------------|");
        System.out.println("|          Name |            Surname |         Phone |                       E-mail |");
        System.out.println("|---------------|--------------------|---------------|------------------------------|");
        for (Student student : students) {
            System.out.println(student.formatForTable());
        }
        System.out.println("|---------------|--------------------|---------------|------------------------------|");

    }

    private static void displayMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1 - Input new student.");
        System.out.println("4 - Display all students.");
        System.out.println("0 - Exit program.");
    }

    private static String userInputMainMenu() {
        List<String> validInputs = Arrays.asList("1", "4", "0");
        Scanner scanner = new Scanner(System.in);
        String input;
        // while input is not valid.
        while( !validInputs.contains(input = scanner.nextLine().trim())){
            System.out.println("Invalid input! Please try again.");
        }
        return input;
    }


}
