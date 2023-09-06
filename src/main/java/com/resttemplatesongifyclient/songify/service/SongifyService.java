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
    public SongifyResponse getAllSongsAsArray() {
        String jsonSongify = songifyServerClient.getAllSongsRequest();
        if (jsonSongify == null) {
            log.error("JSON response was null");
            return null;
        }
        SongifyResponse songifyResponse = songifyServiceMapper.mapJsonToSongifyResponse(jsonSongify);
        if (songifyResponse != null) {
            log.info("Songify fetched: " + songifyResponse);
        }
        return songifyResponse;
    }
    public void getAllSongsAndDisplayEachOnANewLine() {
        String jsonSongify = songifyServerClient.getAllSongsRequest();
        SongifyResponse songifyResponse = songifyServiceMapper.mapJsonToSongifyResponse(jsonSongify);
        if (songifyResponse == null) {
            log.error("SongifyResponse was null");
            return;
        }

        songifyResponse.songs().forEach((index, song) -> {
            log.info("Index: " + index + " Artist: " + song.artist() + " Song Name: " + song.name());
        });
    }
}
