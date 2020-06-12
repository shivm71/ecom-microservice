package com.consumer.service;

import com.consumer.Dao.NotificationDaoImpl;
import com.consumer.Model.Notify;
import com.consumer.Model.NotifyQueueRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.util.JSONPObject;
//import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class NotifyRabbitMQListner implements MessageListener {
//    @Autowired
//    NotificationDaoImpl notificationDaoImpl;

    public void onMessage(Message message) {
        String body=message.toString();
        String arr[]=body.trim().split("\\s+");
         String bodyattr=arr[0];
        bodyattr=bodyattr.substring(7,bodyattr.length()-1);

//        NotifyQueueRequest notifyQueueRequest= null;
//        try {
//            notifyQueueRequest = new ObjectMapper().readValue(bodyattr, NotifyQueueRequest.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(bodyattr);
//        System.out.println(message.getBody());
//        NotifyQueueRequest notifyQueueRequest=new NotifyQueueRequest();
//        Notify notify=notificationDaoImpl.insert(notifyQueueRequest);
//        System.out.println("notify Message From RabbitMQ: " + notify);
    }

}