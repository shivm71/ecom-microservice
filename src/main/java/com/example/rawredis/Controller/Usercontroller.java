package com.example.rawredis.Controller;

import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import com.example.rawredis.Model.QueueRequest;
import com.example.rawredis.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
@RestController
@Slf4j
@RequestMapping(value = "/user")

public class Usercontroller {
    @Autowired
    ProductService productservice;


    @GetMapping(value = "/gettop")
    public ResponseEntity<?> getall()
    {
        log.info("controller get all products");
        return ResponseEntity.ok(productservice.getall());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/fillredis")
    public ResponseEntity<?> fillRedis()
    {
        log.info("controller get all products");
        return ResponseEntity.ok(productservice.fillRedis());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/emptyredis")
    public ResponseEntity<?> emptyRedis()
    {
        log.info("controller get all products");
        return ResponseEntity.ok(productservice.emptyRedis());
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PostMapping(value = "/insert")
    public ResponseEntity<?> insert(@RequestBody Product product){
        log.info("controller insert"+product);
        return ResponseEntity.ok(productservice.insert(product));
    }


    @PostMapping(value = "/view/{id}")
    public ResponseEntity<?> view(@PathVariable("id") String id){
        return ResponseEntity.ok(productservice.getbyid(id)
        );

    }

    @PostMapping(value = "/addtocart")
    public ResponseEntity<?> addtocart(@RequestParam("productid") String productId,@RequestParam("userid") String userId){
        log.info("????????????add to CART by id");
        QueueRequest queuerequest =new QueueRequest(productId,userId);
        return ResponseEntity.ok(productservice.addtocart(queuerequest));

    }

}
