package com.example.rawredis.Dao;

import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import com.example.rawredis.Model.QueueRequest;
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


    @Value("${cache.size}")
    private static int cachesize;

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
    //0-> out of stock
    //1-> product entry 1
    //2-> more then 1 products
    public int order(QueueRequest queueRequest){
        Product product = findbyid(queueRequest.getProductid());
        if(product.getQuantity() == 0){
            return 0;
        } else if(product.getQuantity() == 1){
           update(product);
           return 1;
        }else{
            update(product);
            return 2;
        }
    }
    public void update(Product product){
        product.setQuantity(product.getQuantity()-1);
        productDAO.save(product);
    }

}
