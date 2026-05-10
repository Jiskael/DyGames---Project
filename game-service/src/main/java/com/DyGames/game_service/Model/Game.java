package com.DyGames.game_service.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "descripciomn")
    private String description;

    @Column(nullable = false, length = 150)
    private String developer;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, length = 20)
    private String releaseDate;

    @Column(length = 500)
    private String coverUrl;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private boolean active;
}
