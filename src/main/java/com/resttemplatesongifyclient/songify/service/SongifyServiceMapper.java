package com.resttemplatesongifyclient.songify.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resttemplatesongifyclient.songify.proxy.SongifyRequestVariableName;
import com.resttemplatesongifyclient.songify.proxy.SongifyRequestVariablesongName;
import com.resttemplatesongifyclient.songify.proxy.SongifyResponse;
import com.resttemplatesongifyclient.songify.proxy.SongifyResponseById;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SongifyServiceMapper {

    private final ObjectMapper objectMapper;

    public SongifyServiceMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    SongifyResponse mapJsonToSongifyResponse(String json) {
        try {
            SongifyResponse response = objectMapper.readValue(json, SongifyResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            log.error("SongifyMapper could not map json", e);
            return null;
        }
    }
    SongifyResponseById mapJsonToSongifyResponseById(String json) {
        try {
            SongifyResponseById response = objectMapper.readValue(json, SongifyResponseById.class);
            return response;
        } catch (JsonProcessingException e) {
            log.error("SongifyMapper could not map json", e);
            return null;
        }
    }

    public SongifyRequestVariablesongName mapJsonToSongifyRequestVariableSongName(String json) {
        try {
            SongifyRequestVariablesongName songifyRequestVariablesongName = objectMapper.readValue(json, SongifyRequestVariablesongName.class);
            return songifyRequestVariablesongName;
        } catch (JsonProcessingException e) {
            log.error("SongifyMapper could not map json to SongifyRequest", e);
            return null;
        }
    }
    public SongifyRequestVariableName mapJsonToSongifyRequestVariableName(String json) {
        try {
            SongifyRequestVariableName songifyRequestVariableName = objectMapper.readValue(json, SongifyRequestVariableName.class);
            return songifyRequestVariableName;
        } catch (JsonProcessingException e) {
            log.error("SongifyMapper could not map json to SongifyRequest", e);
            return null;
        }
    }
}
