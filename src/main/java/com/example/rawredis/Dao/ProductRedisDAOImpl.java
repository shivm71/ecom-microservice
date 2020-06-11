package com.example.rawredis.Dao;

import com.example.rawredis.Model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class ProductRedisDAOImpl {
    @Autowired
    RedisTemplate redisTemplate;

    String KEY = "Product";

    @Value("${cache.size}")
    static int cachesize;

    @Autowired
    RedisConnectionFactory factory;

    public boolean isExist(String id) {
        return(redisTemplate.opsForHash().hasKey(KEY,id));
    }

    public boolean isExist(String Key ,String id) {
        return(redisTemplate.opsForHash().hasKey(KEY,id));
    }

    public void insert(Product product){
        if(getSize() >= cachesize){
            deleteOne();
        }
        Map productHash = new ObjectMapper().convertValue(product, Map.class);
        redisTemplate.opsForHash().put(KEY, product.getId(), productHash);
    }
    public void set(String KEY,String id){
        //true -> shows out of stock for particular id in queue as a key
        redisTemplate.opsForHash().put(KEY,id,true);
    }

    public long getSize(){
        return redisTemplate.opsForHash().size(KEY);
    }

    public void deleteOne(){
        Object key = redisTemplate.opsForHash().keys(KEY).iterator().next();
        redisTemplate.opsForHash().delete(KEY,key);

    }

    public void insert(ArrayList<Product> product){
        for (Product entry : product){
            insert(entry);
        }
    }


    public Product findbyid(String id){
        return (Product) redisTemplate.opsForHash().get(KEY,id);

    }
    public List<Product> getAll(){
        Map entries= redisTemplate.opsForHash().entries(KEY);
        ArrayList<Product> listproducts= new ArrayList<>();
        Set< Map.Entry< String,Integer> > st = entries.entrySet();

        for (Map.Entry<?,?> me:st) {
            listproducts.add((Product) new ObjectMapper().convertValue(me.getValue(),Product.class));
        }

        return listproducts;
    }

    public void deleteAll(){
        factory.getConnection().flushDb();
    }

}
