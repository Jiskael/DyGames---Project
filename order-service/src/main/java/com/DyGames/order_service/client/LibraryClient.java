package com.DyGames.order_service.client;

import com.DyGames.order_service.dto.LibraryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "library-service")
public interface LibraryClient {

    @PostMapping("/api/v1/biblioteca")
    Object agregarABiblioteca(@RequestBody LibraryRequest request);
}