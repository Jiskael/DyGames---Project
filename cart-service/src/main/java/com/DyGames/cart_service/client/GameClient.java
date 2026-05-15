package com.DyGames.cart_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-service")
public interface GameClient {

    @GetMapping("/api/v1/juegos/{id}")
    Object buscarJuegoPorId(@PathVariable Long id);
}