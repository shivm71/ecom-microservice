package com.example.rawredis.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
@Getter
@Setter
@ToString
@Document(value = "product")
public class Product implements Serializable {

    private String id;
    private String Product_name;
    private String description;
    private double price;
}
