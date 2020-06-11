package com.example.rawredis.Model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Getter
@Setter
@Repository
@Document(collection = "user")
public class User {
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