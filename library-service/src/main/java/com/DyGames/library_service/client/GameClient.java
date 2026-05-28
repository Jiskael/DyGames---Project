package com.DyGames.library_service.client;

import com.DyGames.library_service.dto.GameRespuesta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service")
public interface GameClient {

    @GetMapping("/api/v1/juegos/{id}")
    GameRespuesta buscarJuegoPorId(@PathVariable Long id);
}
