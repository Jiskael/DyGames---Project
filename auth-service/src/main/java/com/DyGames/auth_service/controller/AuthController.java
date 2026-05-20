package com.DyGames.auth_service.controller;

import com.DyGames.auth_service.dto.AuthRespuesta;
import com.DyGames.auth_service.dto.LoginRequest;
import com.DyGames.auth_service.model.Auth;
import com.DyGames.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Registrar
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody Auth auth) {
        return new ResponseEntity<>(authService.save(auth), HttpStatus.CREATED);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        AuthRespuesta ar = authService.login(request);
        if (ar == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ar);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(authService.findAll());
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Auth auth = authService.findById(id);
        if (auth == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(auth);
    }

    // Actualizar rol o estado
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Auth auth) {
        Auth actualizado = authService.update(id, auth);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Listado DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(authService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarDTO(@PathVariable Long id) {
        AuthRespuesta ar = authService.findDTO(id);
        if (ar == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ar);
    }
}