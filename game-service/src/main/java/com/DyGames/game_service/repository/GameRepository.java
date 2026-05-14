package com.DyGames.game_service.repository;

import com.DyGames.game_service.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByCategoriaId(Long categoriaId);
    List<Game> findByActivoTrue();
    List<Game> findByPrecioBetween(Double min, Double max);
    List<Game> findByDesarrollador(String desarrollador);
    List<Game> findByTituloContainingIgnoreCase(String titulo);
}