package com.example.rawredis.Service;

import com.example.rawredis.Dao.ProductDAO;
import com.example.rawredis.Dao.ProductDAOimpl;
import com.example.rawredis.Dao.ProductRedisDAOImpl;
import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.NotifyRequest;
import com.example.rawredis.Model.Product;
import com.example.rawredis.Model.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRedisDAOImpl productRedisDAOImpl;

    @Autowired
    ProductDAOimpl productDAOimpl;

    @Autowired
    RabbitProducer rabbitProducer;


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

    public Object edit(Product product){
        if (getbyid(product.getId()) == null){
            return "no product available";
        }

        productDAOimpl.update(product);

        notifycheck(product);
        return getbyid(product.getId());
    }

    private void notifycheck(Product product) {
        if(product.getQuantity()>0 && productRedisDAOImpl.isExist("QUEUE",product.getId())){
            rabbitProducer.sendupdate(new UpdateRequest(product.getId()));
            productRedisDAOImpl.deleteOne("QUEUE",product.getId());
        }
    }

    public Collection<ProductDTO> getall(){
        //todo if cache empty then what to do
        List<ProductDTO> list= new ArrayList<>();
        for(Product product:productRedisDAOImpl.getAll()){
            list.add(new ProductDTO(product));
        }

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



    public String addtocart(NotifyRequest notifyRequest){
        // if odd request then sleep 2 sec -> som
        if(productRedisDAOImpl.isExist("QUEUE",notifyRequest.getProductid())){
            rabbitProducer.sendnotify(notifyRequest);
            // Send to rabbit queuw

            return("Product out of stock You'll be notify when ity will be back in stock");
        } else{

            int result=productDAOimpl.order(notifyRequest);

            if(result==0){
                productRedisDAOImpl.set("QUEUE",notifyRequest.getProductid());
                rabbitProducer.sendnotify(notifyRequest);
                return "Product out of stock You'll be notify when ity will be back in stock";

            }else if(result==1){


                productRedisDAOImpl.set("QUEUE",notifyRequest.getProductid());

            }

           return "Order Placed";

        }
    }
//    3 3users 2 1 0->redis->entry




}
