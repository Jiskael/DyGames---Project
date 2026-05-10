package com.DyGames.game_service.Service;

import com.DyGames.game_service.DTO.GameRequestDTO;
import com.DyGames.game_service.DTO.GameResponseDTO;

import java.util.List;

public interface GameService {

    GameResponseDTO create(GameRequestDTO dto);

    GameResponseDTO findById(Long id);

    List<GameResponseDTO> findAll();

    List<GameResponseDTO> findByActive(boolean active);

    List<GameResponseDTO> findByCategoryId(Long categoryId);

    List<GameResponseDTO> findByDeveloper(String developer);

    List<GameResponseDTO> findByTitle(String title);

    GameResponseDTO update(Long id, GameRequestDTO dto);

    void delete(Long id);
}
