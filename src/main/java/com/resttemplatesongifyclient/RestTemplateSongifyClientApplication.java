package com.resttemplatesongifyclient;

import com.resttemplatesongifyclient.songify.service.SongifyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Log4j2
public class RestTemplateSongifyClientApplication {
    private final RestTemplate restTemplate;
    private final SongifyService songifyService;

    public RestTemplateSongifyClientApplication(RestTemplate restTemplate, SongifyService songifyService) {
        this.restTemplate = restTemplate;
        this.songifyService = songifyService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateSongifyClientApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run(){
        songifyService.getAllSongs();
    }

}
