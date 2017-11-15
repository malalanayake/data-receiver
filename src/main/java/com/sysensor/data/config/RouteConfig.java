package com.sysensor.data.config;

import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
public class RouteConfig {

    @Autowired
    BaseConfig baseConfig;


    @Bean
    RouteBuilder bookqueue() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:test").to("initialDataReceiverProcessor")
                       // .shutdownRunningTask(ShutdownRunningTask.CompleteAllTasks)
                        .routeId("test");

                from("amqp://localhost:5672/book.q")
                        .setHeader("priority", constant(9))
                        .to("initialDataReceiverProcessor");
            }
        };
    }
}
