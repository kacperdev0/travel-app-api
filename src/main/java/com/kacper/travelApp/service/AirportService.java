package com.kacper.travelApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Service
public class AirportService {

    private final WebClient webClient;

    public AirportService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://overpass-api.de/api").build();
    }

    public Mono<String> searchAirports(double lat, double lon, int radius) {
        String overpassQuery = String.format(
                "[out:json];node[\"aeroway\"=\"aerodrome\"](around:%d,%f,%f);out body;",
                radius, lat, lon
        );
        String url = "/interpreter?data=" + overpassQuery;

        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> {
                    System.out.println("Response from Overpass API: " + response); // Log the response
                });
    }

}
