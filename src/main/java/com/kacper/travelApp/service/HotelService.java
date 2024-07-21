package com.kacper.travelApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HotelService {

    @Value("${rapidapi.key}")
    private String apiKey;

    private final WebClient webClient;

    public HotelService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> searchHotels(String destinationId, String searchType, String arrivalDate, String departureDate,
                                     int adults, String childrenAge, int roomQty, int pageNumber, String units,
                                     String temperatureUnit, String languageCode, String currencyCode) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/hotels/searchHotels")
                        .queryParam("dest_id", destinationId)
                        .queryParam("search_type", searchType)
                        .queryParam("arrival_date", arrivalDate)
                        .queryParam("departure_date", departureDate)
                        .queryParam("adults", adults)
                        .queryParam("children_age", childrenAge)
                        .queryParam("room_qty", roomQty)
                        .queryParam("page_number", pageNumber)
                        .queryParam("units", units)
                        .queryParam("temperature_unit", temperatureUnit)
                        .queryParam("languagecode", languageCode)
                        .queryParam("currency_code", currencyCode)
                        .build())
                .header("x-rapidapi-key", apiKey)
                .header("x-rapidapi-host", "booking-com15.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class);
    }
}
