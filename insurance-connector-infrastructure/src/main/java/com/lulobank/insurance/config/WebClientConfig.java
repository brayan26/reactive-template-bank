package com.lulobank.insurance.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    @Value("${http-client.timeout}")
    private int timeout;

    @Bean
    public WebClient.Builder webclientBuilder() {
        Duration duration = Duration.ofSeconds(timeout);
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) duration.toMillis())
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler((int) duration.toSeconds()))
                        .addHandlerLast(new WriteTimeoutHandler((int) duration.toSeconds())));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}
