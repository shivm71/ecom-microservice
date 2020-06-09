package com.example.rawredis.Dao;

import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductDAOimpl{
    @Autowired
    ProductDAO productDAO;

    public Product insert(Product product){
       return(productDAO.save(product));
    }
    public Product findbyid(String id){
        Optional<Product> product=productDAO.findById(id);
        return product.orElse(null);
    }

}
