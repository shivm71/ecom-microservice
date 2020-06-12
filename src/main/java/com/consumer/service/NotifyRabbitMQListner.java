package com.consumer.service;

import com.consumer.Dao.NotificationDaoImpl;
import com.consumer.Model.Notify;
import com.consumer.Model.NotifyQueueRequest;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotifyRabbitMQListner implements MessageListener {
//    @Autowired
//    NotificationDaoImpl notificationDaoImpl;

    public void onMessage(Message message) {
        System.out.println(message);
//        NotifyQueueRequest notifyQueueRequest=new NotifyQueueRequest();
//        Notify notify=notificationDaoImpl.insert(notifyQueueRequest);
//        System.out.println("notify Message From RabbitMQ: " + notify);
    }
}