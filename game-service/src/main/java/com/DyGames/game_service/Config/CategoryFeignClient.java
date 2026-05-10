package com.DyGames.game_service.Config;

import com.DyGames.game_service.DTO.CategoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Cliente Feign para consumir el category-service.
 * Ajusta el nombre y la URL según tu configuración de servicio/Eureka.
 */
@FeignClient(name = "category-service", url = "${category.service.url:http://localhost:8082}")
public interface CategoryFeignClient {

    @GetMapping("/api/categories/{id}")
    CategoryResponseDTO findById(@PathVariable Long id);
}
