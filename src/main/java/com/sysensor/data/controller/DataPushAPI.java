package com.sysensor.data.controller;


import com.sysensor.data.config.URLConfig;
import com.sysensor.data.exchange.DataExchange;
import com.sysensor.data.exchange.DataResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConfig.PUSH)
public class DataPushAPI {

    Logger LOG = Logger.getLogger(this.getClass());

    @RequestMapping("/signal")
    @ResponseBody
    public DataExchange pushDataSignal(DataExchange dataExchange) {
        LOG.debug("PUSH-API-SIGNAL:" + dataExchange.toString());
        DataResponse dataResponse = new DataResponse();
        dataResponse.STATUS = false;

        if (dataExchange.SIGNAL != null) {
            dataResponse.STATUS = true;
        }

        dataExchange.DATA_RESPONSE = dataResponse;
        return dataExchange;
    }
}
