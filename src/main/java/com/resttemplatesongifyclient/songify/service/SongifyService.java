package com.resttemplatesongifyclient.songify.service;

import com.resttemplatesongifyclient.songify.proxy.SongifyResponse;
import com.resttemplatesongifyclient.songify.proxy.SongifyServerProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SongifyService {

    private final SongifyServerProxy songifyServerClient;
    private final SongifyServiceMapper songifyServiceMapper;


    public SongifyService(SongifyServerProxy songifyServerClient, SongifyServiceMapper songifyServiceMapper) {
        this.songifyServerClient = songifyServerClient;
        this.songifyServiceMapper = songifyServiceMapper;
    }
    public SongifyResponse getAllSongs() {
        SongifyResponse songifyResponse = songifyServerClient.getAllSongsRequest();
        if (songifyResponse == null) {
            log.error("Response was null");
            return null;
        }
        log.info("Songify fetched: " + songifyResponse);
        return songifyResponse;
    }

}
