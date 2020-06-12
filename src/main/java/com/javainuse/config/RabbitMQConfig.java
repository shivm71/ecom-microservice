package com.javainuse.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javainuse.service.RabbitMQListner;

@Configuration
public class RabbitMQConfig {

//	@Value("${spring.rabbitmq.queue}")
//	String notifyqueue;
//
//	@Value("${spring.rabbitmq.queue}")
//	String updatequeue;
//
//	@Value("${spring.rabbitmq.username}")
//	String username;

//	@Value("${spring.rabbitmq.password}")
//	private String password;

	@Bean
	Queue queue() {
		return new Queue("notify.queue", false);
	}
//
	@Bean
	Queue queue1() {
		return new Queue("update.queue", false);
	}



//	@Bean
//	Queue queue() {
//		return new Queue(queueName, false);
//	}
	
//
//	@Bean
//	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
//		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//		simpleMessageListenerContainer.setQueues(queue());
//
//		simpleMessageListenerContainer.setMessageListener(new RabbitMQListner());
//		return simpleMessageListenerContainer;
//
//	}

	/*@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername(username);
		cachingConnectionFactory.setUsername(password);
		return cachingConnectionFactory;
	}*/
	

	/*@Bean
	MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQListner());
		return simpleMessageListenerContainer;

	}*/
	
	
		
}
