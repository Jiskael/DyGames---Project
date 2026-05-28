package com.DyGames.auth_service.service;

import com.DyGames.auth_service.dto.AuthRespuesta;
import com.DyGames.auth_service.dto.LoginRequest;
import com.DyGames.auth_service.mapper.AuthMapper;
import com.DyGames.auth_service.model.Auth;
import com.DyGames.auth_service.repository.AuthRepository;
import com.DyGames.auth_service.exception.EmailYaRegistradoException;
import com.DyGames.auth_service.exception.CredencialesInvalidasException;
import com.DyGames.auth_service.exception.CuentaDesactivadaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthMapper authMapper;

    // Esto sirve especificamente para encriptar contraseñas
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Registrar
    public Auth save(Auth auth) {
        if (authRepository.existsByEmail(auth.getEmail())) {
            throw new EmailYaRegistradoException("El email ya esta registrado");
        }
        // Encripta la contraseña antes de guardar
        auth.setPassword(encoder.encode(auth.getPassword()));
        return authRepository.save(auth);
    }

    // Metodo Login
    public AuthRespuesta login(LoginRequest request) {
        Auth auth = authRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (auth == null) {
            throw new CredencialesInvalidasException("Credenciales invalidas");
        }

        // Verifica la password encriptada
        if (!encoder.matches(request.getPassword(), auth.getPassword())) {
            throw new CredencialesInvalidasException("Credenciales invalidas");
        }

        if (!auth.getActivo()) {
            throw new CuentaDesactivadaException("Cuenta desactivada");
        }

        return authMapper.toDTO(auth);
    }

    public List<Auth> findAll() {
        return authRepository.findAll();
    }

    public Auth findById(Long id) {
        return authRepository.findById(id).orElse(null);
    }

    public Auth update(Long id, Auth auth) {
        Auth a = authRepository.findById(id).orElse(null);
        if (a == null) return null;
        a.setRol(auth.getRol());
        a.setActivo(auth.getActivo());
        return authRepository.save(a);
    }

    public void delete(Long id) {
        authRepository.deleteById(id);
    }

    // DTO
    public AuthRespuesta findDTO(Long id) {
        return authMapper.toDTO(findById(id));
    }

    public List<AuthRespuesta> findDTOList() {
        return authMapper.toDTOList(findAll());
    }
}