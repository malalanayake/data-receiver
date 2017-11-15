package com.sysensor.data;

import com.sysensor.data.processor.receiver.AMQPReceiver;
import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class DataReceiverStarter {

    public static Logger LOG = Logger.getLogger(DataReceiverStarter.class.getName());

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DataReceiverStarter.class, args);

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            LOG.info(beanName);
        }

        CamelSpringBootApplicationController configurableApplicationContextBean =
                ctx.getBean(CamelSpringBootApplicationController.class);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(ctx.getBean(AMQPReceiver.class,ctx));
        executor.submit(ctx.getBean(AMQPReceiver.class,ctx));

    }
}
