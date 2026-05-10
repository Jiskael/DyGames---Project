package com.DyGames.user_service.Controller;

import com.DyGames.user_service.Service.AuthService;
import com.DyGames.user_service.dto.AuthRequestDTO;
import com.DyGames.user_service.dto.AuthResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //CREATE

    @PostMapping
    public ResponseEntity<AuthResponseDTO> create(@Valid @RequestBody AuthRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.create(dto));
    }

    //READ

    @GetMapping
    public ResponseEntity<List<AuthResponseDTO>> findAll() {
        return ResponseEntity.ok(authService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(authService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AuthResponseDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(authService.findByEmail(email));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<AuthResponseDTO>> findByRole(@PathVariable String role) {
        return ResponseEntity.ok(authService.findByRole(role));
    }

    @GetMapping("/active/{active}")
    public ResponseEntity<List<AuthResponseDTO>> findByActive(@PathVariable boolean active) {
        return ResponseEntity.ok(authService.findByActive(active));
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AuthResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody AuthRequestDTO dto) {
        return ResponseEntity.ok(authService.update(id, dto));
    }

    //DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //VALIDATE CREDENTIALS (usado por otros microservicios via Feign) ───────

    @PostMapping("/validate")
    public ResponseEntity<Map<String, Boolean>> validate(@RequestBody Map<String, String> credentials) {
        boolean valid = authService.validateCredentials(
                credentials.get("email"),
                credentials.get("password")
        );
        return ResponseEntity.ok(Map.of("valid", valid));
    }
}
