package com.DyGames.library_service.controller;

import com.DyGames.library_service.dto.LibraryRespuesta;
import com.DyGames.library_service.model.Library;
import com.DyGames.library_service.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/biblioteca")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(libraryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Library library = libraryService.findById(id);
        if (library == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(library);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Library library) {
        return new ResponseEntity<>(libraryService.save(library), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        libraryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(libraryService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        LibraryRespuesta lr = libraryService.findDTO(id);
        if (lr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(lr);
    }

    // Reportes
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(libraryService.findByUsuario(usuarioId));
    }

    @GetMapping("/juego/{juegoId}")
    public ResponseEntity<?> buscarPorJuego(@PathVariable Long juegoId) {
        return ResponseEntity.ok(libraryService.findByJuego(juegoId));
    }

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarJuego(@RequestParam Long usuarioId,
                                            @RequestParam Long juegoId) {
        boolean tiene = libraryService.tieneJuego(usuarioId, juegoId);
        return ResponseEntity.ok(tiene);
    }
}