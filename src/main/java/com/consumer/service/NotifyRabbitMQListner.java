package com.consumer.service;

import com.consumer.Dao.NotificationDaoImpl;
import com.consumer.Model.Notify;
import com.consumer.Model.NotifyQueueRequest;

import com.consumer.Model.UpdateQueueRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class NotifyRabbitMQListner {
    @Autowired
    NotificationDaoImpl notificationDaoImpl;

    @Autowired
    NotificationService notificationService;

    @RabbitListener(queues = "notify.queue")
    public void notify(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
        try {
            NotifyQueueRequest notifyQueueRequest =  new ObjectMapper().readValue(msg, NotifyQueueRequest.class);
            Notify notify=notificationDaoImpl.insert(notifyQueueRequest);
            System.out.println("notify Message From RabbitMQ: " + notify);

        } catch (JsonProcessingException e) {
            System.out.println(e);
        }

    }
    @RabbitListener(queues = "update.queue")
    public void onUpdate(Message message) {
        String msg = new String(message.getBody());
        System.out.println(msg);
        try {

            UpdateQueueRequest updateQueueRequest = new ObjectMapper().readValue(msg, UpdateQueueRequest.class);
            notificationService.notifyUsers(updateQueueRequest);


        } catch (JsonProcessingException e) {
            System.out.println(e);
        }

    }

}