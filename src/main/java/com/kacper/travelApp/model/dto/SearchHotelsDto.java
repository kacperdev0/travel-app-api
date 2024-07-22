package com.kacper.travelApp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SearchHotelsDto {
    @NotEmpty(message = "Destination ID is required")
    private String destId;

    @NotEmpty(message = "Search type is required")
    private String searchType;

    @NotEmpty(message = "Arrival date is required")
    private String arrivalDate;

    @NotEmpty(message = "Departure date is required")
    private String departureDate;

    @NotNull(message = "Number of adults is required")
    private int adults;

    private String childrenAge;

    @NotNull(message = "Room quantity is required")
    private int roomQty;

    @NotNull(message = "Page number is required")
    private int pageNumber;

    @NotEmpty(message = "Units is required")
    private String units;

    @NotEmpty(message = "Temperature unit is required")
    private String temperatureUnit;

    @NotEmpty(message = "Language code is required")
    private String languageCode;

    @NotEmpty(message = "Currency code is required")
    private String currencyCode;
}
