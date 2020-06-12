package com.consumer.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping(value="/")
    public String back(){
    return "DONE";
}


//for frontend showcasing add aservice to notification service and call repository
    @GetMapping(value="/notification")
    public String notification(){
        return "DONE";
    }
}
