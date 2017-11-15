package com.sysensor.data.processor.common;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RouteInitServiceImpl implements IRouteInitService {

    Logger LOG = Logger.getLogger(this.getClass());

    @Autowired
    ProducerTemplate template;

    @Override
    public Exchange enter(String route, Object data, Map<String, Object> headers) {
        Exchange exchange = new DefaultExchange(template.getCamelContext());
        Message message = exchange.getIn();
        LOG.debug("START: Publishing to Route " + route + "");
        if (headers != null) {
            message.setHeaders(headers);
        }
        LOG.info(message.getHeaders());
        message.setBody(data);
        exchange.setIn(message);
        Exchange exAfter = template.send(route, exchange);

        LOG.debug("END: Publish to Route " + route + "");
        return exAfter;
    }
}

