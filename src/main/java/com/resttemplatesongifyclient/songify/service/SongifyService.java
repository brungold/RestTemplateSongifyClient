package com.resttemplatesongifyclient.songify.service;

import com.resttemplatesongifyclient.songify.proxy.*;
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

    public void getSongById() {
        String jsonSongify = songifyServerClient.getSongById();
        if (jsonSongify == null) {
            log.error("JSON response was null");
            return;
        }
        SongifyResponseById songifyResponseById = songifyServiceMapper.mapJsonToSongifyResponseById(jsonSongify);

        if (songifyResponseById != null) {
            log.info("Song Name: " + songifyResponseById.song().name() +  " "
                    + "Artist: " + songifyResponseById.song().artist());
        } else {
            log.error("SongifyRequest was null");
        }
    }
    public void postSong() {
        SongifyRequest requestBody = new SongifyRequest("Changes", "2Pac");
        String json = songifyServerClient.postSong(requestBody);
        if (json == null) {
            log.error("JSON response was null");
            return;
        }
        SongifyRequest songifyResponseById = songifyServiceMapper.mapJsonToSongifyRequest(json);
    }

}