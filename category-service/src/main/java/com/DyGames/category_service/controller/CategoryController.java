package com.DyGames.category_service.controller;

import com.DyGames.category_service.dto.CategoryRespuesta;
import com.DyGames.category_service.model.Category;
import com.DyGames.category_service.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Gestión de categorías de videojuegos")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(
        summary = "Listar todas las categorías (entidad)",
        description = "Retorna la lista completa de categorías como entidad JPA"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Operation(
        summary = "Buscar categoría por ID (entidad)",
        description = "Retorna una categoría por su ID como entidad JPA"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoría encontrada",
            content = @Content(schema = @Schema(implementation = Category.class))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID de la categoría", required = true)
            @PathVariable Long id) {
        Category cat = categoryService.findById(id);
        if (cat == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cat);
    }

    @Operation(
        summary = "Registrar nueva categoría",
        description = "Crea una nueva categoría de videojuego. El nombre debe ser único."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente",
            content = @Content(schema = @Schema(implementation = Category.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o nombre ya existe", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> registrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos de la categoría a registrar", required = true)
            @Valid @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Actualizar categoría",
        description = "Actualiza los datos de una categoría existente por su ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente",
            content = @Content(schema = @Schema(implementation = Category.class))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID de la categoría a actualizar", required = true)
            @PathVariable Long id,
            @Valid @RequestBody Category category) {
        Category actualizada = categoryService.update(id, category);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @Operation(
        summary = "Eliminar categoría",
        description = "Elimina una categoría por su ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Categoría eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID de la categoría a eliminar", required = true)
            @PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ─── Endpoints DTO ────────────────────────────────────────────────────────

    @Operation(
        summary = "Listar todas las categorías (DTO)",
        description = "Retorna la lista completa de categorías como DTO (CategoryRespuesta)"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente",
        content = @Content(schema = @Schema(implementation = CategoryRespuesta.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(categoryService.findDTOList());
    }

    @Operation(
        summary = "Buscar categoría por ID (DTO)",
        description = "Retorna una categoría por su ID como DTO (CategoryRespuesta)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoría encontrada",
            content = @Content(schema = @Schema(implementation = CategoryRespuesta.class))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID de la categoría", required = true)
            @PathVariable Long id) {
        CategoryRespuesta cr = categoryService.findDTO(id);
        if (cr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cr);
    }

    @Operation(
        summary = "Buscar categoría por nombre",
        description = "Retorna una categoría buscando por su nombre exacto (DTO)"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoría encontrada",
            content = @Content(schema = @Schema(implementation = CategoryRespuesta.class))),
        @ApiResponse(responseCode = "404", description = "Categoría no encontrada", content = @Content)
    })
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(
            @Parameter(description = "Nombre de la categoría", required = true)
            @PathVariable String nombre) {
        CategoryRespuesta cr = categoryService.findByNombreDTO(nombre);
        if (cr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cr);
    }
}
