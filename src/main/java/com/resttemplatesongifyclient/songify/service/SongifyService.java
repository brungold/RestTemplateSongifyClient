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

    public void getSongById(Integer id) {
        String jsonSongify = songifyServerClient.getSongById(id);
        if (jsonSongify == null) {
            log.error("JSON response was null");
            return;
        }
        SongifyResponseById songifyResponseById = songifyServiceMapper.mapJsonToSongifyResponseById(jsonSongify);

        if (songifyResponseById != null) {
            log.info("Song with id: " + id + ". "
                    + "Song Name: " + songifyResponseById.song().name() + " "
                    + "Artist: " + songifyResponseById.song().artist());
        } else {
            log.error("SongifyRequest was null");
        }
    }

    public void postSong(SongifyRequestVariablesongName songifyRequestVariablesongName) {
        String json = songifyServerClient.postSong(songifyRequestVariablesongName);
        if (json == null) {
            log.error("JSON response was null");
            return;
        }
        songifyRequestVariablesongName = songifyServiceMapper.mapJsonToSongifyRequestVariableSongName(json);
        if (songifyRequestVariablesongName != null) {
            log.info("Posted new song");
        } else {
            log.error("SongifyRequest was null");
        }
    }

    public void deleteSongById(Integer id) {
        String deleteResult = songifyServerClient.deleteSongById(id);

        if (deleteResult != null) {
            log.info("Piosenka została usunięta: " + deleteResult);
        } else {
            log.error("Nie udało się usunąć piosenki.");
        }
    }

    public void putSong(Integer id, SongifyRequestVariablesongName songifyRequestVariablesongName) {
        String jsonPutResult = songifyServerClient.putSong(id, songifyRequestVariablesongName);
        if (jsonPutResult == null) {
            log.error("JSON response was null");
            return;
        }
        SongifyRequestVariablesongName updatedSong = songifyServiceMapper.mapJsonToSongifyRequestVariableSongName(jsonPutResult);
        if (updatedSong != null) {
            log.info("Song has been updated: " + updatedSong);
        } else {
            log.error("Song update error.");
        }
    }

    public void patchSong(Integer id, SongifyRequestVariablesongName songifyRequestVariablesongName) {
        String jsonPatchResult = songifyServerClient.patchSong(id, songifyRequestVariablesongName);
        if (jsonPatchResult == null) {
            log.error("JSON response was null");
            return;
        }
        SongifyPatchResponse partiallyUpdatedSong = songifyServiceMapper.mapJsonToSongifyPatchResponse(jsonPatchResult);
        if (partiallyUpdatedSong != null) {
            log.info("Song has been updated. New song name:" + partiallyUpdatedSong.updatedSong().artist() + ", new artist name: " + partiallyUpdatedSong.updatedSong().name());
        } else {
            log.error("Song update error.");
        }
    }
}