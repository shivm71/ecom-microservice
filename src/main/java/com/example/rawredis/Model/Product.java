package com.example.rawredis.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@Document(value = "product")
public class Product implements Serializable {
    @Id
    private String id;
    private String product_name;
    private String description;
    private double price;
    private int quantity;
}
