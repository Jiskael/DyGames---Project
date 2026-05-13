package com.DyGames.category_service.controller;

import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.model.Category;
import com.DyGames.category_service.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Category cat = categoryService.findById(id);
        if (cat == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cat);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Category category) {
        Category actualizada = categoryService.update(id, category);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(categoryService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        CategoryRespuesta cr = categoryService.findDTO(id);
        if (cr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cr);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable String nombre) {
        CategoryRespuesta cr = categoryService.findByNombreDTO(nombre);
        if (cr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cr);
    }
}