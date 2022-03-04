package com.springboot.rsocket.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RestController
public class ConsumerController {
	
	private Logger log = LoggerFactory.getLogger(ConsumerController.class);
	
    @Autowired
    private RSocketRequester rSocketRequester;

    @GetMapping(value = "/requestResponse/{message}")
    public Mono<String> requestResponse(@PathVariable("message") String message) {
        log.info("send request:{}", message);
        return rSocketRequester
                .route("/request-response")
                .data(message)
                .retrieveMono(String.class);
    }

    @GetMapping(value = "/fireAndForget/{message}")
    public Mono<Void> fireAndForget(@PathVariable("message") String message) {
        log.info("send request:{}", message);
        return rSocketRequester
                .route("/fire-and-forget")
                .data(message)
                .send();
    }

    @GetMapping(value = "/requestStream/{message}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> requestStream(@PathVariable("message") String message) {
        return rSocketRequester
                .route("/request-stream")
                .data(message)
                .retrieveFlux(String.class);
    }

    @GetMapping(value = "/requestChannel")
    public void requestChannel() {
        Mono<String> setting1 = Mono.just("Hello1");
        Mono<String> setting2 = Mono.just("Hello2").delayElement(Duration.ofSeconds(2));
        Mono<String> setting3 = Mono.just("Hello3").delayElement(Duration.ofSeconds(5));

        Flux<String> settings = Flux.concat(setting1, setting2, setting3)
                .doOnNext(d -> log.info("Sending request of {}.", d));
        this.rSocketRequester
                .route("request-channel")
                .data(settings)
                .retrieveFlux(String.class)
                .subscribe(data -> log.info("Received: {} \n(Type 's' to stop.)", data));
    }
}
