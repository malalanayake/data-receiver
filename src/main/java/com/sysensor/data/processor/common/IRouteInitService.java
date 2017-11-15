package com.sysensor.data.processor.common;

import org.apache.camel.Exchange;

import java.util.Map;

public interface IRouteInitService {

    Exchange enter(String route, Object data, Map<String, Object> headers);
}

