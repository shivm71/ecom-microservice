package com.example.rawredis.Service;

import com.example.rawredis.Dao.ProductDAO;
import com.example.rawredis.Dao.ProductDAOimpl;
import com.example.rawredis.Dao.ProductRedisDAOImpl;
import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRedisDAOImpl productRedisDAOImpl;
    @Autowired
    ProductDAOimpl productDAOimpl;
    public ProductDTO getbyid(String id){
        if(productRedisDAOImpl.isExist(id)){
            return new ProductDTO(productRedisDAOImpl.findbyid(id));
        }
        else{
            Product product =  productDAOimpl.findbyid(id);
            if(product!=null){
                productRedisDAOImpl.insert(product);
                return new ProductDTO(product);
            }
            return null;
        }

    }
    public Collection<ProductDTO> getall(){
        //todo if cache empty then what to do
        List<ProductDTO> list= new ArrayList<>();
        for(Product product:productRedisDAOImpl.getAll()){
            list.add(new ProductDTO(product));
        }

        log.info("services get all products"+list);
        return list;
    }
    public String fillRedis(){
        try {
            productRedisDAOImpl.insert(productDAOimpl.getTen());
        }catch (Exception e){
            return "Redis not active check the server";
        }
        return "Redis Filling Complete";
    }
    public ProductDTO insert(Product product)
    {
        log.info("product service"+product);
//        productRedisDAOImpl.insert(product);
//        return new ProductDTO(product);
        return new ProductDTO(productDAOimpl.insert(product));
    }

    public String emptyRedis(){
        try{
         productRedisDAOImpl.deleteAll();
        }catch(Exception e){
            return "Redis Not Active Check Server";
        }
        return "Flushing Done";
    }


}
