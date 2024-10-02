package com.kacper.travelApp.model.dto;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class OverwritePlanDto {
    private long id;
    private String locations;
}
