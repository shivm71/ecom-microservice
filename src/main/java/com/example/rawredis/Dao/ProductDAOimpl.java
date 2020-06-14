package com.example.rawredis.Dao;

import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import com.example.rawredis.Model.NotifyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAOimpl{
    @Autowired
    ProductDAO productDAO;
    @Autowired
    ProductRedisDAOImpl productRedisDAOImpl;

    @Value("${cache.size}")
    private int cachesize;

    public Product insert(Product product)
    {

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
    public int order(NotifyRequest queueRequest){
        Product product = findbyid(queueRequest.getProductid());
        if(product.getQuantity() == 0){
            return 0;
        } else if(product.getQuantity() == 1){
           updateQuantity(product);
           return 1;
        }else{
            updateQuantity(product);
            return 2;
        }
    }
    public void updateQuantity(Product product){
        product.setQuantity(product.getQuantity()-1);
        updateRedis(product);
        productDAO.save(product);
    }


    public void update(Product product){
        updateRedis(product);
        productDAO.save(product);
    }
    public void updateRedis(Product product){
        boolean isPresent = productRedisDAOImpl.isExist(product.getId());
        if(isPresent && product.getQuantity()>0){
            productRedisDAOImpl.insert(product);
        }
        if(isPresent && product.getQuantity()<=0){
            productRedisDAOImpl.deleteOne(product.getId());
        }
    }

}
