package com.DyGames.user_service.controller;

import com.DyGames.user_service.dto.UserRespuesta;
import com.DyGames.user_service.model.User;
import com.DyGames.user_service.service.UserService;
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
@RequestMapping("/api/v1/usuarios")
@Tag(name = "User Service", description = "Gestión de perfiles de usuario de DyGames")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Listar todos los usuarios", description = "Retorna la lista completa de usuarios registrados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Buscar usuario por ID", description = "Retorna un usuario según su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del usuario", example = "1") @PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Registrar nuevo usuario", description = "Crea un nuevo perfil de usuario. El username debe ser único en el sistema.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o username ya existe")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar usuario", description = "Modifica los datos de perfil de un usuario existente (username, email, nombre, foto)")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del usuario a actualizar", example = "1") @PathVariable Long id,
            @Valid @RequestBody User user) {
        User actualizado = userService.update(id, user);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina el perfil de un usuario por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID del usuario a eliminar", example = "1") @PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar usuarios con detalle (DTO)", description = "Retorna todos los usuarios con información de autenticación expandida")
    @ApiResponse(responseCode = "200", description = "Lista con detalle obtenida exitosamente")
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(userService.findDTOList());
    }

    @Operation(summary = "Buscar usuario con detalle por ID (DTO)", description = "Retorna un usuario con información de autenticación completa")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado con detalle"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID del usuario", example = "1") @PathVariable Long id) {
        UserRespuesta ur = userService.findDTO(id);
        if (ur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ur);
    }

    @Operation(summary = "Buscar usuario por username", description = "Retorna el perfil completo de un usuario buscando por su nombre de usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Username no existe")
    })
    @GetMapping("/username/{username}")
    public ResponseEntity<?> buscarPorUsername(
            @Parameter(description = "Nombre de usuario único", example = "gamer123") @PathVariable String username) {
        UserRespuesta ur = userService.findByUsernameDTO(username);
        if (ur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ur);
    }

    @Operation(summary = "Buscar usuario por email", description = "Retorna el perfil completo de un usuario buscando por su correo electrónico")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Email no registrado")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(
            @Parameter(description = "Correo electrónico del usuario", example = "usuario@email.com") @PathVariable String email) {
        UserRespuesta ur = userService.findByEmailDTO(email);
        if (ur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ur);
    }
}
