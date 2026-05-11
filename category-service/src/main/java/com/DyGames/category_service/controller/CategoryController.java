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

    //Por revisar - 11-05-26
    @PostMapping
    public ResponseEntity<CategoryRespuesta> crearCategoria(@RequestBody @Valid CategoryRequest request) {
        return ResponseEntity.status(201).body(categoryService.crearCategory(request));
    }

    @GetMapping
    public ResponseEntity<List<Category>> obtenerCategorias() {
        return ResponseEntity.ok(categoryService.getAll());
    }

}
