package com.kacper.travelApp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SearchHotelsDto {
    @NotEmpty(message = "Latitude name is required")
    private double latitude;
    @NotEmpty(message = "Longitude name is required")
    private double longitude;

}
