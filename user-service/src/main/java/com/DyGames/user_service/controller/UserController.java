package com.DyGames.user_service.controller;

import com.DyGames.user_service.dto.UserRequest;
import com.DyGames.user_service.dto.UserRespuesta;
import com.DyGames.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    //crear
    @PostMapping
    public ResponseEntity<UserRespuesta> crear(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(userService.crearUsuario(request));
    }

    //obtener todos
    @GetMapping
    public ResponseEntity<List<UserRespuesta>> obtenerTodosUsuarios() {
        return ResponseEntity.ok(userService.obtenerTodos());
    }

    //Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<UserRespuesta> BuscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(userService.buscarPorId(id));
    }

    //Buscar por username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserRespuesta> buscarPorUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.buscarPorNombre(username));
    }

    //Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserRespuesta> buscarPorEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.buscarPorEmail(email));
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<UserRespuesta> eliminar(@PathVariable Long id){
        userService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
