package com.resttemplatesongifyclient.songify.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;
@JsonIgnoreProperties
public record SongifyResponse(Map<Integer, SongifyRequestVariableName> songs) {
}
