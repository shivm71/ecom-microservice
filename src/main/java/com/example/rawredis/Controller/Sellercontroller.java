package com.example.rawredis.Controller;

import com.example.rawredis.Dto.ProductDTO;
import com.example.rawredis.Model.Product;
import com.example.rawredis.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
@RestController
public class Sellercontroller {
    @Autowired
    ProductService productservice;
    @GetMapping(value = "/gettop")
    public ResponseEntity<?> getall(){
        return ResponseEntity.ok(productservice.getall());
    }
    @PostMapping(value = "/insert")
    public ResponseEntity<?> insert(@RequestBody Product product){
        return ResponseEntity.ok(productservice.insert(product));
    }

    @GetMapping(value = "view/{id}")
    public ResponseEntity<?> view(@RequestParam("id") String id ){
        return ResponseEntity.ok(productservice.getbyid(id)
        );

    }

}
