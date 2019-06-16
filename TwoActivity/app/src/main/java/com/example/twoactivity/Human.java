package com.example.twoactivity;

import java.io.Serializable;

public class Human implements Serializable {
    String name;
    int age;
    String email;

    Human (String name, int age, String email) {
        this.age = age;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAge: " + age + "\nEmail: " + email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}