package com.kacper.travelApp.model;

import com.kacper.travelApp.repository.UserRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @ManyToOne
    @JoinColumn(name="user")
    private User user;
    @Column(name = "locations")
    private String locations;
    @Column(name = "saveTime")
    private String saveTime;
    @Column(name = "isPublic")
    private boolean isPublic;

    public Plan() {

    }

    public Plan(User user, String locations) {
        this.user = user;
        this.locations = locations;
        this.saveTime = LocalDateTime.now().toString();
        this.isPublic = false;
    }
}
