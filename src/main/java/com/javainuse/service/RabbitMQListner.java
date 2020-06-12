package com.javainuse.service;

import com.javainuse.Model.NotifyQueueRequest;
import com.javainuse.Model.UpdateQueueRequest;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;

//@Service
//public class RabbitMQListner implements MessageListener {
//	@RabbitListener(queues = "product.queue")
//	public void onproduct(Message message) {
//		System.out.println("Consuming Message - " + new String(message.getBody()));
//	}
//	@RabbitListener(queues = "update.queue")
//	public void onupdate(Message message) {
//		System.out.println("Consuming Message - " + new String(message.getBody()));
//	}
//
//}

@Service
public class RabbitMQListner{

//	@RabbitListener(queues = "${spring.rabbitmq.queue}")
//NotifyQueueRequest notifyqueueRequest
	@RabbitListener(queues = "notify.queue")
		public void notifyqueue(UpdateQueueRequest updateQueueRequest) {
			System.out.println("Notify Message From RabbitMQ: " + updateQueueRequest);
		}
	@RabbitListener(queues = "update.queue")
	public void updatequeue(UpdateQueueRequest updateQueueRequest) {
		System.out.println("UPdate Message From RabbitMQ: " + updateQueueRequest);
	}
}