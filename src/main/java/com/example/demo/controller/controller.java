package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RestController
public class controller {
    @RequestMapping(value = "/")
    public String toprint()
    {
        return "Hello World";
    }
}
