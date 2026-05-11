package com.DyGames.category_service.controller;

import com.DyGames.category_service.dto.CategoryRequest;
import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.model.Category;
import com.DyGames.category_service.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Crear categoria
    @PostMapping
    public ResponseEntity<CategoryRespuesta> crearCategoria(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.status(201).body(categoryService.crearCategory(request));
    }

    //Obtener categorias
    @GetMapping
    public ResponseEntity<List<CategoryRespuesta>> obtenerCategorias() {
        return ResponseEntity.ok(categoryService.ObtenerTodasLasCategorias());
    }

    //Buscar categoria por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<CategoryRespuesta> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(categoryService.BuscarPorNombre(nombre));
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<CategoryRespuesta> actualizarCategoria( @PathVariable Long id, @RequestBody @Valid CategoryRequest r) {
        return ResponseEntity.ok(categoryService.Actualizar(id,r));
    }

    //y Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryRespuesta> eliminarCategoria(@PathVariable Long id) {
        categoryService.Eliminar(id);
        return ResponseEntity.noContent().build();

    }
}
