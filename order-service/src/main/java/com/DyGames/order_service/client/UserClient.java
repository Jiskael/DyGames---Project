package com.DyGames.order_service.client;

import com.DyGames.order_service.dto.UserRespuesta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/v1/usuarios/{id}")
    UserRespuesta buscarUsuarioPorId(@PathVariable Long id);
}
