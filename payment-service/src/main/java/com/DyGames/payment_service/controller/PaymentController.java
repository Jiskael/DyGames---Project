package com.DyGames.payment_service.controller;

import com.DyGames.payment_service.dto.PaymentRespuesta;
import com.DyGames.payment_service.model.Payment;
import com.DyGames.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagos")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        if (payment == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.save(payment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Payment payment) {
        Payment actualizado = paymentService.update(id, payment);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(paymentService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        PaymentRespuesta pr = paymentService.findDTO(id);
        if (pr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pr);
    }

    // Reportes
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(paymentService.findByUsuario(usuarioId));
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<?> buscarPorOrden(@PathVariable Long ordenId) {
        return ResponseEntity.ok(paymentService.findByOrden(ordenId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(paymentService.findByEstado(estado));
    }

    @GetMapping("/metodo/{metodo}")
    public ResponseEntity<?> buscarPorMetodo(@PathVariable String metodo) {
        return ResponseEntity.ok(paymentService.findByMetodo(metodo));
    }
}