package com.resttemplatesongifyclient.songify.proxy;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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

    public String getSongById() {
        //GET http://localhost:8080/songs
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs")
                .path("/1");
        try {
            ResponseEntity<String >response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String .class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }
    public String getAllSongsRequest() {
        //GET http://localhost:8080/songs
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs");
        try {
            ResponseEntity<String >response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.GET,
                    null,
                    String .class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    public String  postSong() {
        //GET http://localhost:8080/songs
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs");
        SongifyRequestVariablesongName songifyRequestVariablesongName = new SongifyRequestVariablesongName("Nowa", "Piosenka");
        HttpEntity<SongifyRequestVariablesongName> httpEntity = new HttpEntity<>(songifyRequestVariablesongName);
        try {
            ResponseEntity<String >response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.POST,
                    httpEntity,
                    String .class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }
    public String deleteSongById(Integer id) {
        //GET http://localhost:8080/songs
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs/" + id);
        try {
            ResponseEntity<String >response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.DELETE,
                    null,
                    String .class
            );
            return response.getBody();
        } catch (RestClientResponseException exception) {
            log.error(exception.getStatusText() + " " + exception.getStatusCode().value());
        } catch (RestClientException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }
    public String putSong(Integer id, SongifyRequestVariablesongName songifyRequestVariablesongName) {
        //GET http://localhost:8080/songs
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs/" + id);
        HttpEntity<SongifyRequestVariablesongName> httpEntity = new HttpEntity<>(songifyRequestVariablesongName);
        try {
            ResponseEntity<String >response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.PUT,
                    httpEntity,
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

    public String patchSong(Integer id, SongifyRequestVariablesongName songifyRequestVariablesongName) {
        //GET http://localhost:8080/songs
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/songs/" + id);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<SongifyRequestVariablesongName> httpEntity = new HttpEntity<>(songifyRequestVariablesongName, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.PATCH,
                    httpEntity,
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
