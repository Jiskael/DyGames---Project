package com.DyGames.game_service.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String developer;
    private double price;
    private String releaseDate;
    private String coverUrl;
    private Long categoryId;
    private String categoryName; // enriquecido via Feign al category-service
    private boolean active;
}
