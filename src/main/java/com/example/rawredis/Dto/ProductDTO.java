package com.example.rawredis.Dto;

import com.example.rawredis.Model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {
    private String id;
    private String product_name;
    private String description;
    private double price;
    private int quantity;


    public ProductDTO(){}

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public ProductDTO(Product product){
        this.id = product.getId();
        this.product_name=product.getProduct_name();
        this.description=product.getDescription();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}