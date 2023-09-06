package com.resttemplatesongifyclient.songify.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;

@Component
@Log4j2
public class SongifyServerProxy {

    private final RestTemplate restTemplate;

    public SongifyServerProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${songify-server.service.url}")
    String url;

    @Value("${songify-server.service.port}")
    int port;

    public String getAllSongsRequest() {
        //GET http://localhost:8080/songs
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs");
        try {
            ResponseEntity<String>response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String.class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }
}
