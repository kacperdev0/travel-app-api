package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.dto.SearchHotelsDto;
import com.kacper.travelApp.service.HotelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("api/hotels")
@Validated
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/searchHotels")
    public Mono<String> searchHotels(@Valid @RequestBody SearchHotelsDto params) {
        return hotelService.searchHotels(
                params.getDestId(), params.getSearchType(), params.getArrivalDate(),
                params.getDepartureDate(), params.getAdults(), params.getChildrenAge(),
                params.getRoomQty(), params.getPageNumber(), params.getUnits(),
                params.getTemperatureUnit(), params.getLanguageCode(), params.getCurrencyCode()
        );
    }
}
