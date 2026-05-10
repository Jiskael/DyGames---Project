package com.DyGames.user_service.Service;

import com.DyGames.user_service.dto.AuthRequestDTO;
import com.DyGames.user_service.dto.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    public AuthResponseDTO create(AuthRequestDTO dto);

    public AuthResponseDTO findById(Long id);

    public AuthResponseDTO findByEmail(String email);

    public List<AuthResponseDTO> findAll();

    public List<AuthResponseDTO> findByRole(String role);

    public List<AuthResponseDTO> findByActive(boolean active);

    public AuthResponseDTO update(Long id, AuthRequestDTO dto);

    public void delete(Long id);

    public boolean validateCredentials(String email, String rawPassword);
}
