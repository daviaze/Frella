package com.daviazevedodev.frella.Model;

public class User {
    public String name, email, phone, city;

    public User(){

    }

    public User(String name, String email, String tell, String city) {
        this.name = name;
        this.email = email;
        this.phone = tell;
        this.city = city;
    }
}
