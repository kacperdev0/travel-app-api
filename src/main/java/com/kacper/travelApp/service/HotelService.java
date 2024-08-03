package com.kacper.travelApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HotelService {
    private final WebClient webClient;

    public HotelService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> searchHotels(double latitude, double longitude, int radius) {
        String query = String.format(
                "[out:json];\n" +
                        "node[\"tourism\"=\"hotel\"](around:%d, %f, %f);\n" +
                        "out body;",
                radius, latitude, longitude
        );

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/interpreter")
                        .queryParam("data", query)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
