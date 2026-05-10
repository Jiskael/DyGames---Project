package com.DyGames.game_service.Repository;

import com.DyGames.game_service.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByActive(boolean active);

    List<Game> findByCategoryId(Long categoryId);

    List<Game> findByDeveloperIgnoreCase(String developer);

    List<Game> findByTitleContainingIgnoreCase(String title);

    List<Game> findByCategoryIdAndActive(Long categoryId, boolean active);

    boolean existsByTitleIgnoreCase(String title);
}
