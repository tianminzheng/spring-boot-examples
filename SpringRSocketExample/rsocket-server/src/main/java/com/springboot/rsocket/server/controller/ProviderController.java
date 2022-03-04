package com.springboot.rsocket.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ProviderController {
	
	private Logger log = LoggerFactory.getLogger(ProviderController.class);
	
    @MessageMapping("/request-response")
    public Mono<String> requestResponse(String request) {
        log.info("receive request:{}", request);
        return Mono.just("Hello " + request);
    }

    @MessageMapping("/fire-and-forget")
    public Mono<Void> fireAndForget(String request) {
        log.info("receive request:{}", request);
        return Mono.empty();
    }

    @MessageMapping("/request-stream")
    public Flux<String> requestStream(String request) {
        log.info("receive request:{}", request);
        return Flux.just("hello", request, "welcome");
    }

    @MessageMapping("/request-channel")
    Flux<String> requestChannel(Flux<String> request) {
        return request
                .doOnNext(record -> log.info("record is {}.", record))
                .map(record -> "response from server to " + record)
                .log();
    }

    @MessageExceptionHandler
    public Mono<String> handleException(Exception e) {
        return Mono.just(e.getMessage());
    }

}
