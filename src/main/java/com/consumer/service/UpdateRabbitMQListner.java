//package com.consumer.service;
//
//import com.consumer.Model.UpdateQueueRequest;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class UpdateRabbitMQListner implements MessageListener {
//	@Autowired
//	NotificationService notificationService;
//	@RabbitListener(queues = "update.queue")
//	public void onMessage(Message message) {
//
////		System.out.println("UPdate Message From RabbitMQ: " + message);
//		UpdateQueueRequest updateQueueRequest = new UpdateQueueRequest();
//		notificationService.notifyUsers(updateQueueRequest);
//	}
//}