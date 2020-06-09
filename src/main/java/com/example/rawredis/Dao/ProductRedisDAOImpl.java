package com.example.rawredis.Dao;

import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public class ProductRedisDAOImpl {
    @Autowired
    RedisTemplate<String, Product> redisTemplate;

    public boolean isExist(String id){
        return(redisTemplate.hasKey(id));
    }
    public void insert(Product product){
       redisTemplate.opsForValue().set(product.getId(),product);
    }
    public Product findbyid(String id){
        return redisTemplate.opsForValue().get(id);

    }
    public List<Product> getAll(){
       Set<String> keys= redisTemplate.keys("*");
       ArrayList<Product> listproducts= new ArrayList<>();
       for(String key:keys){
           listproducts.add(redisTemplate.opsForValue().get(key));
       }
       return listproducts;
    }

}
