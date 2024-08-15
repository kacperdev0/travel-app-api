package com.kacper.travelApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "hotel")
    private String hotel;
    @Column(name = "airportArrival")
    private String airportArrival;
    @Column(name = "airportDeparture")
    private String airportDeparture;
}
