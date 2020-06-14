package com.example.rawredis.Controller;

import com.example.rawredis.Model.NotifyRequest;
import com.example.rawredis.Model.Product;
import com.example.rawredis.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController

public class Usercontroller {
    @Autowired
    ProductService productservice;


    @GetMapping(value = "/gettop")
    public ResponseEntity<?> getall()
    {
        return ResponseEntity.ok(productservice.getall());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/fillredis")
    public ResponseEntity<?> fillRedis()
    {

        return ResponseEntity.ok(productservice.fillRedis());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/emptyredis")
    public ResponseEntity<?> emptyRedis()
    {

        return ResponseEntity.ok(productservice.emptyRedis());
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PostMapping(value = "/insert")
    public ResponseEntity<?> insert(@RequestBody Product product){

        return ResponseEntity.ok(productservice.insert(product));
    }


    @PostMapping(value = "/view/{id}")
    public ResponseEntity<?> view(@PathVariable("id") String id){
        return ResponseEntity.ok(productservice.getbyid(id)
        );

    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PostMapping(value = "/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody Product product){
        return ResponseEntity.ok(productservice.edit(product));

    }

    @PostMapping(value = "/addtocart")
    public ResponseEntity<?> addtocart(@RequestParam("productid") String productId,@RequestParam("userid") String userId){

        NotifyRequest notifyRequest =new NotifyRequest(productId,userId);
        // raname thread to som
        // if userid -> odd -> rename som
        return ResponseEntity.ok(productservice.addtocart(notifyRequest));

    }





}
