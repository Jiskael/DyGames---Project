package com.DyGames.library_service.client;

import com.DyGames.library_service.dto.OrderRespuesta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service")
public interface OrderClient {

    @GetMapping("/api/v1/ordenes/{id}")
    OrderRespuesta buscarOrdenPorId(@PathVariable Long id);
}
