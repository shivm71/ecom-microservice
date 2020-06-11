package com.example.rawredis.Dto;

import com.example.rawredis.Model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;


@Getter
@Setter
@ToString
public class ProductDTO {
    private String id;
    private String product_name;
    private String description;
    private double price;
    private ArrayList<String> user_ordered = new ArrayList<>();
    private int quantity;


    public ProductDTO(){}
    public ProductDTO(Product product){
        this.id = product.getId();
        this.product_name=product.getProduct_name();
        this.description=product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}