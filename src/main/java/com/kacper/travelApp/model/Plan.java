package com.kacper.travelApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name="userId")
    private long userId;
    @Column(name = "hotel")
    private String hotel;
    @Column(name = "airportArrival")
    private String airportArrival;
    @Column(name = "airportDeparture")
    private String airportDeparture;
    @Column(name = "saveTime")
    private String saveTime;
    @Column(name = "isPublic")
    private boolean isPublic;

    public Plan() {

    }

    public Plan(long userId, String hotel, String airportArrival, String airportDeparture) {
        this.userId = userId;
        this.hotel = hotel;
        this.airportArrival = airportArrival;
        this.airportDeparture = airportDeparture;
        this.saveTime = LocalDateTime.now().toString();
        this.isPublic = false;
    }
}
