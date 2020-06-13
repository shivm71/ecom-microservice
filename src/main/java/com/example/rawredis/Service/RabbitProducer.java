package com.example.rawredis.Service;

import com.example.rawredis.Model.NotifyRequest;
import com.example.rawredis.Model.UpdateRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class RabbitProducer{

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${queue.rabbitmq.exchange}")
    private String exchange;

    @Value("${notify.rabbitmq.routingkey}")
    private String notifyroutingkey;

    @Value("${update.rabbitmq.routingkey}")
    private String updateroutingkey;

    public void sendupdate(UpdateRequest company) {
        amqpTemplate.convertAndSend(exchange, updateroutingkey, company);
        System.out.println("Send msg = " + company);

    }

    public void sendnotify(NotifyRequest company) {
        amqpTemplate.convertAndSend(exchange, notifyroutingkey, company);
        System.out.println("Send msg = " + company);

    }
}
