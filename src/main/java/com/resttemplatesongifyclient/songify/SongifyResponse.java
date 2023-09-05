package com.resttemplatesongifyclient.songify;

import java.util.Map;

public record SongifyResponse(Map<Integer, SongifyResponse> songs) {
}
