package com.DyGames.game_service.Controller;

import com.DyGames.game_service.DTO.GameRequestDTO;
import com.DyGames.game_service.DTO.GameResponseDTO;
import com.DyGames.game_service.Service.GameService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // ─── CREATE ────────────────────────────────────────────────────────────────

    @PostMapping
    public ResponseEntity<GameResponseDTO> create(@Valid @RequestBody GameRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(dto));
    }

    // ─── READ ──────────────────────────────────────────────────────────────────

    @GetMapping
    public ResponseEntity<List<GameResponseDTO>> findAll() {
        return ResponseEntity.ok(gameService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.findById(id));
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<List<GameResponseDTO>> findByActive(@PathVariable boolean active) {
        return ResponseEntity.ok(gameService.findByActive(active));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<GameResponseDTO>> findByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(gameService.findByCategoryId(categoryId));
    }

    @GetMapping("/developer/{developer}")
    public ResponseEntity<List<GameResponseDTO>> findByDeveloper(@PathVariable String developer) {
        return ResponseEntity.ok(gameService.findByDeveloper(developer));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GameResponseDTO>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(gameService.findByTitle(title));
    }

    // ─── UPDATE ────────────────────────────────────────────────────────────────

    @PutMapping("/{id}")
    public ResponseEntity<GameResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody GameRequestDTO dto) {
        return ResponseEntity.ok(gameService.update(id, dto));
    }

    // ─── DELETE ────────────────────────────────────────────────────────────────

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
