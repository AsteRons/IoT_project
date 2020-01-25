package com.iotproject.server.iotserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klasa główna dla naszej aplikacji
 * Dzięki adnotasji SpringBootApplication są wywoływane inne klasy dzieki wykorzystaniu Spring Boot
 */
@SpringBootApplication
public class IotserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotserverApplication.class, args);
    }

}
