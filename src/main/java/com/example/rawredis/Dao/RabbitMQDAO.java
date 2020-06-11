package com.example.rawredis.Dao;



import com.example.rawredis.Model.QueueRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQDAO {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${product.rabbitmq.exchange}")
    private String exchange;

    @Value("${product.rabbitmq.routingkey}")
    private String routingkey;

    public void sendtoqueue(QueueRequest request) {
        amqpTemplate.convertAndSend(exchange, routingkey, request);
        System.out.println("Send msg = " + request);

    }
}
