package com.sysensor.data.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class URLConfig {
    public static final String API = "/api";
    public static final String USER = API + "/user";
    public static final String PUSH = API + "/push";
}
