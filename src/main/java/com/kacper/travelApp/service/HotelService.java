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

    public Mono<String> searchHotels(double lat, double lon, int radius) {
        String query = String.format(
                "[out:json];\n" +
                        "node[\"tourism\"=\"hotel\"](around:%d, %f, %f);\n" +
                        "out body;",
                radius, lat, lon
        );
        String url = "/interpreter?data=" + query;

        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> {
                    System.out.println("Response from Overpass API: " + response); // Log the response
                });
    }
}
