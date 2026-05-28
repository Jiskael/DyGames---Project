package com.DyGames.game_service.client;

import com.DyGames.game_service.dto.DeveloperRespuesta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "developer-service")
public interface DeveloperClient {

    @GetMapping("/api/v1/desarrolladores/{id}")
    DeveloperRespuesta buscarDesarrolladorPorId(@PathVariable Long id);
}
