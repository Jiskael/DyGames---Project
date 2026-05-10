package com.DyGames.game_service.Config;

import com.DyGames.game_service.Repository.GameRepository;
import com.DyGames.game_service.Model.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final GameRepository gameRepository;

    @Override
    public void run(String... args) {
        if (gameRepository.count() == 0) {
            log.info("Cargando datos iniciales de game-service...");

            List<Game> games = List.of(
                    Game.builder()
                            .title("The Legend of Zelda: Breath of the Wild")
                            .description("Juego de aventura en mundo abierto ambientado en Hyrule.")
                            .developer("Nintendo")
                            .price(59.99)
                            .releaseDate("2017-03-03")
                            .coverUrl("https://example.com/covers/zelda-botw.jpg")
                            .categoryId(1L)
                            .active(true)
                            .build(),
                    Game.builder()
                            .title("God of War")
                            .description("Kratos y su hijo Atreus emprenden un viaje por los reinos nórdicos.")
                            .developer("Santa Monica Studio")
                            .price(49.99)
                            .releaseDate("2018-04-20")
                            .coverUrl("https://example.com/covers/god-of-war.jpg")
                            .categoryId(1L)
                            .active(true)
                            .build(),
                    Game.builder()
                            .title("Minecraft")
                            .description("Juego de construcción y supervivencia en un mundo de bloques.")
                            .developer("Mojang Studios")
                            .price(26.95)
                            .releaseDate("2011-11-18")
                            .coverUrl("https://example.com/covers/minecraft.jpg")
                            .categoryId(2L)
                            .active(true)
                            .build(),
                    Game.builder()
                            .title("Cyberpunk 2077")
                            .description("RPG de mundo abierto en la megalópolis futurista Night City.")
                            .developer("CD Projekt Red")
                            .price(39.99)
                            .releaseDate("2020-12-10")
                            .coverUrl("https://example.com/covers/cyberpunk.jpg")
                            .categoryId(3L)
                            .active(false)
                            .build()
            );

            gameRepository.saveAll(games);
            log.info("✅ {} juegos cargados correctamente.", games.size());
        } else {
            log.info("La base de datos ya contiene registros. DataLoader omitido.");
        }
    }
}
