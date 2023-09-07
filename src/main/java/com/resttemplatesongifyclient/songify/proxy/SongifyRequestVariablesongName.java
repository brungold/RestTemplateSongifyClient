package com.resttemplatesongifyclient.songify.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SongifyRequestVariablesongName(String songName, String artist) {
}
