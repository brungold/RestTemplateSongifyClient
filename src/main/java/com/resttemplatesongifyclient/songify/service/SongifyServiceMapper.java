package com.resttemplatesongifyclient.songify.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resttemplatesongifyclient.songify.proxy.SongifyResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SongifyServiceMapper {

    private final ObjectMapper objectMapper;

    public SongifyServiceMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    SongifyResponse mapJsonToSongifyResponse(String json){
        try {
            SongifyResponse response = objectMapper.readValue(json, SongifyResponse.class);
            return response;
        } catch (JsonProcessingException e) {
            log.error("SongifyMapper could not map json", e);
            return null;
        }
    }
}
