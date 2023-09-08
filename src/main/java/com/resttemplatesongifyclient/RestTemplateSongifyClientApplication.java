package com.resttemplatesongifyclient;

import com.resttemplatesongifyclient.songify.proxy.SongifyPatchResponse;
import com.resttemplatesongifyclient.songify.proxy.SongifyRequestVariableName;
import com.resttemplatesongifyclient.songify.proxy.SongifyRequestVariablesongName;
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
    public void run() {
        //songifyService.getAllSongsAsArray();
        //songifyService.getAllSongsAndDisplayEachOnANewLine();
        //songifyService.getSongById();
        //songifyService.postSong();
        //songifyService.deleteSongById(2);
        //songifyService.putSong(1, new SongifyRequestVariablesongName("Nuevo cancion", "artist"));
        songifyService.patchSong(1, new SongifyRequestVariablesongName("Nuevo cancion", "artist"));
    }
}
