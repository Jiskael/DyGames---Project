package com.DyGames.game_service.repository;

import com.DyGames.game_service.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByCategoriaId(Long categoriaId);
    List<Game> findByActivoTrue();
    List<Game> findByPrecioBetween(Double min, Double max);
    List<Game> findByDesarrolladorId(Long desarrolladorId);
    List<Game> findByTituloContainingIgnoreCase(String titulo);
}
