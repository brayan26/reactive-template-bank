package com.lulobank.insurance.server;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "http-client")
public class RequestConfig {
    private int timeout;
    private int retry;
    private int duration;
}

