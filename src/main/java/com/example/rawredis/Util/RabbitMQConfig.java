package com.example.rawredis.Util;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${notify.rabbitmq.queue}")
    String notifyqueueName;

    @Value("${update.rabbitmq.queue}")
    String updatequeueName;

    @Value("${queue.rabbitmq.exchange}")
    String exchange;


    @Bean
    Queue NotifyQueue() {

        return new Queue(notifyqueueName, false);
    }

    @Bean
    Queue UpdateQueue() {

        return new Queue(updatequeueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }


    @Bean
    Binding Notifybinding(Queue NotifyQueue, DirectExchange exchange) {
        return BindingBuilder.bind(NotifyQueue).to(exchange).with("NotifyQueue");
    }

    @Bean
    Binding UpdateBinding(Queue UpdateQueue, DirectExchange exchange) {
        return BindingBuilder.bind(UpdateQueue).to(exchange).with("UpdateQueue");
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
