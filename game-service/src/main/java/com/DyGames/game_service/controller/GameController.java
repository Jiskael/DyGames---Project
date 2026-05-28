package com.DyGames.game_service.controller;

import com.DyGames.game_service.dto.GameRespuesta;
import com.DyGames.game_service.model.Game;
import com.DyGames.game_service.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/juegos")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(gameService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Game game = gameService.findById(id);
        if (game == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(game);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Game game) {
        return new ResponseEntity<>(gameService.save(game), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Game game) {
        Game actualizado = gameService.update(id, game);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(gameService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        GameRespuesta gr = gameService.findDTO(id);
        if (gr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(gr);
    }

    // Reportes
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<?> buscarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(gameService.findByCategoria(categoriaId));
    }

    @GetMapping("/activos")
    public ResponseEntity<?> listarActivos() {
        return ResponseEntity.ok(gameService.findActivos());
    }

    @GetMapping("/precio")
    public ResponseEntity<?> buscarPorRangoPrecio(@RequestParam Double min,
                                                  @RequestParam Double max) {
        return ResponseEntity.ok(gameService.findByRangoPrecio(min, max));
    }

    @GetMapping("/desarrollador/{desarrolladorId}")
    public ResponseEntity<?> buscarPorDesarrollador(@PathVariable Long desarrolladorId) {
        return ResponseEntity.ok(gameService.findByDesarrollador(desarrolladorId));
    }

    @GetMapping("/buscar/{titulo}")
    public ResponseEntity<?> buscarPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(gameService.findByTitulo(titulo));
    }
}
