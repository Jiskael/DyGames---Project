package com.DyGames.developer_service.controller;

import com.DyGames.developer_service.dto.DeveloperRespuesta;
import com.DyGames.developer_service.model.Developer;
import com.DyGames.developer_service.service.DeveloperService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/desarrolladores")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(developerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Developer developer = developerService.findById(id);
        if (developer == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(developer);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Developer developer) {
        return new ResponseEntity<>(developerService.save(developer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Developer developer) {
        Developer actualizado = developerService.update(id, developer);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        developerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(developerService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        DeveloperRespuesta dr = developerService.findDTO(id);
        if (dr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dr);
    }
}
