package com.kacper.travelApp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SearchAirportsDto {
    @NotEmpty(message = "Latitude name is required")
    private double latitude;
    @NotEmpty(message = "Longitude name is required")
    private double longitude;
}
