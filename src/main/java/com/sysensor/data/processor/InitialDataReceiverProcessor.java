package com.sysensor.data.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class InitialDataReceiverProcessor implements Processor {
    public static Logger LOG = Logger.getLogger(InitialDataReceiverProcessor.class.getName());

    @Override
    public void process(Exchange exchange) throws Exception {
        LOG.info("Test This");
    }
}
