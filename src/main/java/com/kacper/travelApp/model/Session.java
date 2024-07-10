package com.kacper.travelApp.model;

import jakarta.persistence.*;
import lombok.Data;

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
}
