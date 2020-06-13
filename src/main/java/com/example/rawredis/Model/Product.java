package com.example.rawredis.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;


@Document(value = "product")
public class Product implements Serializable {
    @Id
    private String id;
    private String product_name;
    private String description;
    private double price;
    private int quantity;
    private ArrayList<String> user_ordered = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> getUser_ordered() {
        return user_ordered;
    }

    public void setUser_ordered(ArrayList<String> user_ordered) {
        this.user_ordered = user_ordered;
    }


}
