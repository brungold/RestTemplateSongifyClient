package com.resttemplatesongifyclient.songify.proxy;

import java.util.Map;

public record SongifyResponse(Map<Integer, SongifyResponse> songs) {
}
