package com.DyGames.game_service.client;

import com.DyGames.game_service.dto.CategoryRespuesta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service")
public interface CategoryClient {

    @GetMapping("/api/v1/categorias/{id}")
    CategoryRespuesta buscarCategoriaPorId(@PathVariable Long id);
}
