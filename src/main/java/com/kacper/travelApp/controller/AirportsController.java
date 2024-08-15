package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.dto.SearchHotelsDto;
import com.kacper.travelApp.service.AirportService;
import com.kacper.travelApp.service.HotelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api/airports")
@Validated
public class AirportsController {

    private final AirportService airportService;

    public AirportsController(AirportService airportService) {
        this.airportService = airportService;
    }


    @PostMapping("/searchAirports")
    public Mono<String> searchHotels(@Valid @RequestBody SearchHotelsDto params) {
        return airportService.searchAirports(
                params.getLatitude(),
                params.getLongitude(),
                80000
        );
    }
}
