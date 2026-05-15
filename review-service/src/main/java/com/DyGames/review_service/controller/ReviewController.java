package com.DyGames.review_service.controller;

import com.DyGames.review_service.dto.ReviewRespuesta;
import com.DyGames.review_service.model.Review;
import com.DyGames.review_service.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resenas")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Review review = reviewService.findById(id);
        if (review == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(review);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Review review) {
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Review review) {
        Review actualizado = reviewService.update(id, review);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(reviewService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        ReviewRespuesta rr = reviewService.findDTO(id);
        if (rr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(rr);
    }

    // Reportes
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(reviewService.findByUsuario(usuarioId));
    }

    @GetMapping("/juego/{juegoId}")
    public ResponseEntity<?> buscarPorJuego(@PathVariable Long juegoId) {
        return ResponseEntity.ok(reviewService.findByJuego(juegoId));
    }

    @GetMapping("/calificacion/{calificacion}")
    public ResponseEntity<?> buscarPorCalificacion(@PathVariable Integer calificacion) {
        return ResponseEntity.ok(reviewService.findByCalificacion(calificacion));
    }
}