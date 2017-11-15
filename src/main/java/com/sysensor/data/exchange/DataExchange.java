package com.sysensor.data.exchange;

import org.springframework.stereotype.Component;

@Component
public class DataExchange {

    public Signal SIGNAL;
    public DataResponse DATA_RESPONSE;

    @Override
    public String toString() {
        return "[" + SIGNAL.toString() + ", " + DATA_RESPONSE.toString() + "]";
    }
}
