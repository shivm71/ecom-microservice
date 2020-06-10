package com.example.rawredis.Dao;

import com.example.rawredis.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends MongoRepository<Product,String> {
//    public Object insert(Product product);


}
