package com.example.rawredis.Dto;

import com.example.rawredis.Model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@ToString
public class ProductDTO {
    private String Product_name;
    private String description;
    private double price;
    public ProductDTO(){}
    public ProductDTO(Product product){
        this.Product_name=product.getProduct_name();
        this.description=product.getDescription();
        this.price = product.getPrice();
    }
}