package com.kacper.travelApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "planId")
    private long planId;

    public Post(long planId) {
        this.id = id;
        this.planId = planId;
    }
}
