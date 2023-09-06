package com.resttemplatesongifyclient.songify.proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SongifyRequest(String name, String artist) {
}
