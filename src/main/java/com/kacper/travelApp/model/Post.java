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

    @OneToOne
    @JoinColumn(name = "planId")
    private Plan plan;

    public Post() {}

    public Post(Plan plan) {
        this.plan = plan;
    }
}
