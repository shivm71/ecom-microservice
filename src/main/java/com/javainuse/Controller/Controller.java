package com.javainuse.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
@GetMapping(value="/")
    public String back(){
    return "DONE";
}
}
