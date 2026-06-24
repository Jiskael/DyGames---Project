package com.DyGames.game_service.controller;

import com.DyGames.game_service.dto.GameRespuesta;
import com.DyGames.game_service.model.Game;
import com.DyGames.game_service.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/juegos")
@Tag(name = "Game Service", description = "Gestión del catálogo de videojuegos de DyGames")
public class GameController {

    @Autowired
    private GameService gameService;

    @Operation(summary = "Listar todos los juegos", description = "Retorna la lista completa de juegos en formato entidad")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(gameService.findAll());
    }

    @Operation(summary = "Buscar juego por ID", description = "Retorna un juego según su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Juego encontrado"),
        @ApiResponse(responseCode = "404", description = "Juego no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del juego", example = "1") @PathVariable Long id) {
        Game game = gameService.findById(id);
        if (game == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(game);
    }

    @Operation(summary = "Registrar nuevo juego", description = "Crea un nuevo juego en el catálogo")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Juego creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Game game) {
        return new ResponseEntity<>(gameService.save(game), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar juego", description = "Modifica los datos de un juego existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Juego actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Juego no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del juego a actualizar", example = "1") @PathVariable Long id,
            @Valid @RequestBody Game game) {
        Game actualizado = gameService.update(id, game);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar juego", description = "Elimina un juego del catálogo por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Juego eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Juego no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del juego a eliminar", example = "1") @PathVariable Long id) {
        gameService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar juegos con detalle (DTO)", description = "Retorna todos los juegos con información de categoría y desarrollador expandida")
    @ApiResponse(responseCode = "200", description = "Lista con detalle obtenida exitosamente")
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(gameService.findDTOList());
    }

    @Operation(summary = "Buscar juego con detalle por ID (DTO)", description = "Retorna un juego con información completa de categoría y desarrollador")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Juego encontrado con detalle"),
        @ApiResponse(responseCode = "404", description = "Juego no encontrado")
    })
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID del juego", example = "1") @PathVariable Long id) {
        GameRespuesta gr = gameService.findDTO(id);
        if (gr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(gr);
    }

    @Operation(summary = "Buscar juegos por categoría", description = "Retorna todos los juegos que pertenecen a una categoría específica")
    @ApiResponse(responseCode = "200", description = "Lista filtrada por categoría")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<?> buscarPorCategoria(
            @Parameter(description = "ID de la categoría", example = "2") @PathVariable Long categoriaId) {
        return ResponseEntity.ok(gameService.findByCategoria(categoriaId));
    }

    @Operation(summary = "Listar juegos activos", description = "Retorna únicamente los juegos con estado activo = true")
    @ApiResponse(responseCode = "200", description = "Lista de juegos activos")
    @GetMapping("/activos")
    public ResponseEntity<?> listarActivos() {
        return ResponseEntity.ok(gameService.findActivos());
    }

    @Operation(summary = "Buscar por rango de precio", description = "Retorna juegos cuyo precio está entre el mínimo y máximo indicados")
    @ApiResponse(responseCode = "200", description = "Lista filtrada por rango de precio")
    @GetMapping("/precio")
    public ResponseEntity<?> buscarPorRangoPrecio(
            @Parameter(description = "Precio mínimo", example = "5.0") @RequestParam Double min,
            @Parameter(description = "Precio máximo", example = "60.0") @RequestParam Double max) {
        return ResponseEntity.ok(gameService.findByRangoPrecio(min, max));
    }

    @Operation(summary = "Buscar juegos por desarrollador", description = "Retorna todos los juegos de un desarrollador específico")
    @ApiResponse(responseCode = "200", description = "Lista filtrada por desarrollador")
    @GetMapping("/desarrollador/{desarrolladorId}")
    public ResponseEntity<?> buscarPorDesarrollador(
            @Parameter(description = "ID del desarrollador", example = "1") @PathVariable Long desarrolladorId) {
        return ResponseEntity.ok(gameService.findByDesarrollador(desarrolladorId));
    }

    @Operation(summary = "Buscar juegos por título", description = "Búsqueda parcial e insensible a mayúsculas por título del juego")
    @ApiResponse(responseCode = "200", description = "Lista de juegos que coinciden con el título")
    @GetMapping("/buscar/{titulo}")
    public ResponseEntity<?> buscarPorTitulo(
            @Parameter(description = "Texto a buscar en el título", example = "witcher") @PathVariable String titulo) {
        return ResponseEntity.ok(gameService.findByTitulo(titulo));
    }
}
