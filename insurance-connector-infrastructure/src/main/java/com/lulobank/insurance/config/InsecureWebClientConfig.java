package com.lulobank.insurance.config;

import java.time.Duration;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

public final class InsecureWebClientConfig {

    public static HttpClient create(final Duration duration) {
        try {
            SslContext sslContext = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build();
            return HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) duration.toMillis())
                    .doOnConnected(conn -> conn
                            .addHandlerLast(new ReadTimeoutHandler((int) duration.toSeconds()))
                            .addHandlerLast(new WriteTimeoutHandler((int) duration.toSeconds())))
                    .secure(spec -> spec.sslContext(sslContext));

        } catch (SSLException e) {
            throw new RuntimeException("Error creating insecure WebClient", e);
        }
    }
}
