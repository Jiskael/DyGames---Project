package com.DyGames.game_service.Mapper;

import com.DyGames.game_service.DTO.GameRequestDTO;
import com.DyGames.game_service.DTO.GameResponseDTO;
import com.DyGames.game_service.Model.Game;

import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    /**
     * Convierte GameRequestDTO → Game (entidad)
     */
    public Game toEntity(GameRequestDTO dto) {
        return Game.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .developer(dto.getDeveloper())
                .price(dto.getPrice())
                .releaseDate(dto.getReleaseDate())
                .coverUrl(dto.getCoverUrl())
                .categoryId(dto.getCategoryId())
                .active(dto.isActive())
                .build();
    }

    /**
     * Convierte Game (entidad) → GameResponseDTO (sin nombre de categoría)
     */
    public GameResponseDTO toResponseDTO(Game game) {
        return GameResponseDTO.builder()
                .id(game.getId())
                .title(game.getTitle())
                .description(game.getDescription())
                .developer(game.getDeveloper())
                .price(game.getPrice())
                .releaseDate(game.getReleaseDate())
                .coverUrl(game.getCoverUrl())
                .categoryId(game.getCategoryId())
                .active(game.isActive())
                .build();
    }

    /**
     * Convierte Game → GameResponseDTO enriquecido con el nombre de la categoría.
     */
    public GameResponseDTO toResponseDTOWithCategory(Game game, String categoryName) {
        GameResponseDTO dto = toResponseDTO(game);
        dto.setCategoryName(categoryName);
        return dto;
    }

    /**
     * Actualiza los campos de una entidad existente con los datos del DTO.
     */
    public void updateEntityFromDTO(GameRequestDTO dto, Game game) {
        game.setTitle(dto.getTitle());
        game.setDescription(dto.getDescription());
        game.setDeveloper(dto.getDeveloper());
        game.setPrice(dto.getPrice());
        game.setReleaseDate(dto.getReleaseDate());
        game.setCoverUrl(dto.getCoverUrl());
        game.setCategoryId(dto.getCategoryId());
        game.setActive(dto.isActive());
    }
}
