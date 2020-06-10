package com.example.rawredis.Dao;

import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class ProductDAOimpl{
    @Autowired
    ProductDAO productDAO;

//    @Value("${cachesize}")
    private static int cachesize = 10;

    public Product insert(Product product)
    {
        log.info("ProductDAOO impl"+product);
       return(productDAO.save(product));
    }
    public Product findbyid(String id){
        Optional<Product> product=productDAO.findById(id);
        return product.orElse(null);
    }
    public ArrayList<Product> getTen(){
        List<Product> entries = productDAO.findAll();
        int total = Math.min(cachesize,entries.size());
        ArrayList<Product> product = new ArrayList<>();
        for(int i =0 ;i<total;i++){
                product.add(entries.get(i));
        }
        return product;
    }

}
