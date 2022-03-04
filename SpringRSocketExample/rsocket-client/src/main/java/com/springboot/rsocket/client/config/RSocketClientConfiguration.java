package com.springboot.rsocket.client.config;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class RSocketClientConfiguration {

    @Bean
    public RSocketStrategies rSocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2JsonEncoder()))
                .decoders(decoders -> decoders.add(new Jackson2JsonDecoder()))
                .build();
    }

    @Bean
    public RSocketRequester getRSocketRequester(RSocketRequester.Builder builder) {
        return builder
                .rsocketConnector(rSocketConnector -> rSocketConnector.reconnect(Retry.fixedDelay(2, Duration.ofSeconds(2))))
                .dataMimeType(MediaType.APPLICATION_JSON)
                .transport(TcpClientTransport.create(7000));
    }
}
