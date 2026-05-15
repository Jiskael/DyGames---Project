package com.DyGames.order_service.controller;

import com.DyGames.order_service.dto.OrderRespuesta;
import com.DyGames.order_service.model.Order;
import com.DyGames.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }

    // Crear orden desde el carrito
    @PostMapping("/crear/{usuarioId}")
    public ResponseEntity<?> crearOrden(@PathVariable Long usuarioId) {
        return new ResponseEntity<>(orderService.crearOrden(usuarioId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Order order) {
        Order actualizado = orderService.update(id, order);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(orderService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        OrderRespuesta or = orderService.findDTO(id);
        if (or == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(or);
    }

    // Reportes
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(orderService.findByUsuario(usuarioId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(orderService.findByEstado(estado));
    }
}