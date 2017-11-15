package com.sysensor.data.processor.receiver;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.sysensor.data.config.BaseConfig;
import com.sysensor.data.processor.common.IRouteInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AMQPReceiver implements Runnable {
    @Autowired
    BaseConfig baseConfig;

    @Autowired
    IRouteInitService routeInitService;

    ApplicationContext ctx;

    public AMQPReceiver(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public void receive(String[] args) throws Exception {
        Channel channel = baseConfig.connection().createChannel();
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicQos(1);
        channel.basicConsume("trade.eq.q", false, consumer);

        while (true) {
            int numMsgs = args.length > 0 ? new Integer(args[0]).intValue() : 1;
            for (int i = 0; i < numMsgs; i++) {
                QueueingConsumer.Delivery msg = consumer.nextDelivery();
                System.out.println("message received: " + new String(msg.getBody()));
                routeInitService.enter("direct:test", msg, null);
                channel.basicAck(msg.getEnvelope().getDeliveryTag(), false);
            }
        }
    }


    @Override
    public void run() {
        try {
            AMQPReceiver amqpReceiver = ctx.getBean(AMQPReceiver.class);
            String[] recArgs = new String[0];
            amqpReceiver.receive(recArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
