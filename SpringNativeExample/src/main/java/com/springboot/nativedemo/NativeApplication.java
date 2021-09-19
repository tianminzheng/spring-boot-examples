package com.springboot.nativedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class NativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NativeApplication.class, args);
    }

    @GetMapping
    public String hello() {
        return "hello";
    }
}
