package com.sysensor.data.config;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.camel.component.amqp.AMQPComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Configuration
public class BaseConfig {

    @PostConstruct
    public void config() {
        Channel channel = null;
        try {
            channel = connection().createChannel();
            //create the durable exchanges
            channel.exchangeDeclare("flow.fx", "fanout", true);
            channel.exchangeDeclare("orders.dx", "direct", true);
            System.out.println("exchanges created.");

            //create the durable queues
            channel.queueDeclare("book.q", true, false, false, null);
            channel.queueDeclare("music.q", true, false, false, null);
            channel.queueDeclare("movie.q", true, false, false, null);
            channel.queueDeclare("order.q", true, false, false, null);
            channel.queueDeclare("flow.q", true, false, false, null);
            channel.queueDeclare("trade.eq.q", true, false, false, null);
            channel.queueDeclare("trade.1.q", true, false, false, null);
            channel.queueDeclare("trade.2.q", true, false, false, null);
            channel.queueDeclare("workflow.q", true, false, false, null);
            channel.queueDeclare("sync.q", true, false, false, null);
            System.out.println("queues created.");

            //create the bindings
            channel.queueBind("flow.q", "flow.fx", "");
            channel.queueBind("book.q", "orders.dx", "book");
            channel.queueBind("music.q", "orders.dx", "music");
            channel.queueBind("movie.q", "orders.dx", "movie");
            System.out.println("bindings created.");
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    @Bean
    public ConnectionFactory rabbitMQConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        return connectionFactory;
    }

    @Bean
    public Connection connection() {
        Connection connection = null;
        try {
            connection = rabbitMQConnectionFactory().newConnection();
        } catch (Exception ex) {

        }
        return connection;
    }

    @Bean
    public AMQPComponent amqp() {
        AMQPComponent amqp = AMQPComponent.amqpComponent("amqp://localhost:5672", "admin", "admin");
        return amqp;
    }
}
