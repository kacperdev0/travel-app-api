package com.kacper.travelApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "SESSIONID")
    private String JSSESSIONID;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "created_at")
    private Long createdAt;

    public void updateCreatedAt() {
        this.createdAt = Instant.now().toEpochMilli();
    }

    public boolean isSessionActive() {
        long currentTimeMillis = Instant.now().toEpochMilli();
        long sessionDurationMillis = currentTimeMillis - this.createdAt;
        long sessionDurationMinutes = sessionDurationMillis / 1000 / 60;
        return sessionDurationMinutes < 30;
    }
}
