package com.DyGames.library_service.controller;

import com.DyGames.library_service.dto.LibraryRespuesta;
import com.DyGames.library_service.model.Library;
import com.DyGames.library_service.service.LibraryService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/biblioteca")
@Tag(name = "Biblioteca", description = "Gestión de la biblioteca de juegos del usuario")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Operation(
        summary = "Listar toda la biblioteca (entidad)",
        description = "Retorna todos los registros de biblioteca como entidad JPA"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente")
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(libraryService.findAll());
    }

    @Operation(
        summary = "Buscar registro de biblioteca por ID (entidad)",
        description = "Retorna un registro de biblioteca por su ID como entidad JPA"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Registro encontrado",
            content = @Content(schema = @Schema(implementation = Library.class))),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del registro de biblioteca", required = true)
            @PathVariable Long id) {
        Library library = libraryService.findById(id);
        if (library == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(library);
    }

    @Operation(
        summary = "Agregar juego a la biblioteca",
        description = "Registra un juego en la biblioteca del usuario. No permite duplicados (mismo usuario + juego)."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Juego agregado exitosamente",
            content = @Content(schema = @Schema(implementation = Library.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o juego ya en biblioteca", content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> registrar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos del registro de biblioteca a crear", required = true)
            @Valid @RequestBody Library library) {
        return new ResponseEntity<>(libraryService.save(library), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Eliminar registro de biblioteca",
        description = "Elimina un registro de biblioteca por su ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Registro eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del registro a eliminar", required = true)
            @PathVariable Long id) {
        libraryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ─── Endpoints DTO ────────────────────────────────────────────────────────

    @Operation(
        summary = "Listar toda la biblioteca (DTO)",
        description = "Retorna todos los registros de biblioteca enriquecidos con datos de usuario, juego y orden"
    )
    @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente",
        content = @Content(schema = @Schema(implementation = LibraryRespuesta.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(libraryService.findDTOList());
    }

    @Operation(
        summary = "Buscar registro de biblioteca por ID (DTO)",
        description = "Retorna un registro de biblioteca enriquecido por su ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Registro encontrado",
            content = @Content(schema = @Schema(implementation = LibraryRespuesta.class))),
        @ApiResponse(responseCode = "404", description = "Registro no encontrado", content = @Content)
    })
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID del registro de biblioteca", required = true)
            @PathVariable Long id) {
        LibraryRespuesta lr = libraryService.findDTO(id);
        if (lr == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(lr);
    }

    // ─── Reportes ─────────────────────────────────────────────────────────────

    @Operation(
        summary = "Obtener biblioteca de un usuario",
        description = "Retorna todos los juegos en la biblioteca de un usuario específico"
    )
    @ApiResponse(responseCode = "200", description = "Biblioteca del usuario obtenida exitosamente",
        content = @Content(schema = @Schema(implementation = LibraryRespuesta.class)))
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(
            @Parameter(description = "ID del usuario", required = true)
            @PathVariable Long usuarioId) {
        return ResponseEntity.ok(libraryService.findByUsuario(usuarioId));
    }

    @Operation(
        summary = "Obtener usuarios que poseen un juego",
        description = "Retorna todos los registros de biblioteca asociados a un juego específico"
    )
    @ApiResponse(responseCode = "200", description = "Registros encontrados exitosamente",
        content = @Content(schema = @Schema(implementation = LibraryRespuesta.class)))
    @GetMapping("/juego/{juegoId}")
    public ResponseEntity<?> buscarPorJuego(
            @Parameter(description = "ID del juego", required = true)
            @PathVariable Long juegoId) {
        return ResponseEntity.ok(libraryService.findByJuego(juegoId));
    }

    @Operation(
        summary = "Verificar si un usuario tiene un juego",
        description = "Retorna true si el usuario ya tiene el juego en su biblioteca, false en caso contrario"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Verificación realizada",
            content = @Content(schema = @Schema(implementation = Boolean.class)))
    })
    @GetMapping("/verificar")
    public ResponseEntity<?> verificarJuego(
            @Parameter(description = "ID del usuario", required = true) @RequestParam Long usuarioId,
            @Parameter(description = "ID del juego", required = true)   @RequestParam Long juegoId) {
        boolean tiene = libraryService.tieneJuego(usuarioId, juegoId);
        return ResponseEntity.ok(tiene);
    }
}
