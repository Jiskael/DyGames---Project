package com.DyGames.user_service.controller;

import com.DyGames.user_service.dto.UserRespuesta;
import com.DyGames.user_service.model.User;
import com.DyGames.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody User user) {
        User actualizado = userService.update(id, user);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints DTO
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(userService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id) {
        UserRespuesta ur = userService.findDTO(id);
        if (ur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ur);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> buscarPorUsername(@PathVariable String username) {
        UserRespuesta ur = userService.findByUsernameDTO(username);
        if (ur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ur);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        UserRespuesta ur = userService.findByEmailDTO(email);
        if (ur == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ur);
    }
}