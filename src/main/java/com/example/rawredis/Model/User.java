package com.example.rawredis.Model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
@Document(collection = "user")
public class User {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getOrdered_product() {
        return ordered_product;
    }

    public void setOrdered_product(ArrayList<String> ordered_product) {
        this.ordered_product = ordered_product;
    }

    //    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    private String username;
    private ArrayList<String> roles;
    private String email;
    private String password;
    private ArrayList<String> ordered_product = new ArrayList<>();
    public User(){}



}