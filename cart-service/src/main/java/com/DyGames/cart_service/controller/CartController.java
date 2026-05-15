package com.DyGames.cart_service.controller;

import com.DyGames.cart_service.dto.CartRespuesta;
import com.DyGames.cart_service.model.Cart;
import com.DyGames.cart_service.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carrito")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Cart cart = cartService.findById(id);
        if (cart == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<?> agregar(@Valid @RequestBody Cart cart) {
        return new ResponseEntity<>(cartService.save(cart), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/vaciar/{usuarioId}")
    public ResponseEntity<?> vaciar(@PathVariable Long usuarioId) {
        cartService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(cartService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        CartRespuesta cr = cartService.findDTO(id);
        if (cr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cr);
    }

    // Reporte
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(cartService.findByUsuario(usuarioId));
    }
}