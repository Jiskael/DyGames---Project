package com.DyGames.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "cart-service")
public interface CartClient {

    @GetMapping("/api/v1/carrito/usuario/{usuarioId}")
    List<Object> obtenerCarrito(@PathVariable Long usuarioId);

    @DeleteMapping("/api/v1/carrito/vaciar/{usuarioId}")
    void vaciarCarrito(@PathVariable Long usuarioId);
}