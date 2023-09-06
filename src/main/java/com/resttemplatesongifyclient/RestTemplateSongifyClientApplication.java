package com.resttemplatesongifyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RestTemplateSongifyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateSongifyClientApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run()

}
