package com.kacper.travelApp.controller;

import com.kacper.travelApp.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/searchHotels")
    public Mono<String> searchHotels(
            @RequestParam String destId,
            @RequestParam String searchType,
            @RequestParam String arrivalDate,
            @RequestParam String departureDate,
            @RequestParam int adults,
            @RequestParam String childrenAge,
            @RequestParam int roomQty,
            @RequestParam int pageNumber,
            @RequestParam String units,
            @RequestParam String temperatureUnit,
            @RequestParam String languageCode,
            @RequestParam String currencyCode) {
        return hotelService.searchHotels(destId, searchType, arrivalDate, departureDate, adults, childrenAge, roomQty,
                pageNumber, units, temperatureUnit, languageCode, currencyCode);
    }
}
