package com.kcs.first.example.vo;

/**
 * Created by Askew on 3/7/2017.
 */
public class Student {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;

    public Student(String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString(){
        return this.getName() + " " + this.getSurname();
    }

    public String formatForTable(){
        return "|" + String.format("%15s", name) + "|" + String.format("%20s", surname)
                + "|" + String.format("%15s", phone) + "|" + String.format("%30s", email) + "|" ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
