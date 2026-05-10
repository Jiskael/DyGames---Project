package com.DyGames.game_service.Service;

import com.DyGames.game_service.Config.CategoryFeignClient;
import com.DyGames.game_service.DTO.CategoryResponseDTO;
import com.DyGames.game_service.DTO.GameRequestDTO;
import com.DyGames.game_service.DTO.GameResponseDTO;
import com.DyGames.game_service.Mapper.GameMapper;
import com.DyGames.game_service.Model.Game;
import com.DyGames.game_service.Repository.GameRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final CategoryFeignClient categoryFeignClient;

    // ─── Helpers ───────────────────────────────────────────────────────────────

    /**
     * Intenta obtener el nombre de la categoría via Feign.
     * Si el category-service no responde, retorna null para no romper el flujo.
     */
    private String getCategoryName(Long categoryId) {
        try {
            CategoryResponseDTO category = categoryFeignClient.findById(categoryId);
            return category != null ? category.getName() : null;
        } catch (Exception e) {
            log.warn("No se pudo obtener la categoría con id {}: {}", categoryId, e.getMessage());
            return null;
        }
    }

    private GameResponseDTO enrichWithCategory(Game game) {
        String categoryName = getCategoryName(game.getCategoryId());
        return gameMapper.toResponseDTOWithCategory(game, categoryName);
    }

    // ─── CRUD ──────────────────────────────────────────────────────────────────

    @Override
    @Transactional
    public GameResponseDTO create(GameRequestDTO dto) {
        // Validar que la categoría existe antes de guardar
        getCategoryName(dto.getCategoryId()); // lanza excepción si no existe

        Game game = gameMapper.toEntity(dto);
        Game saved = gameRepository.save(game);
        log.info("Juego creado con id: {}", saved.getId());
        return enrichWithCategory(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public GameResponseDTO findById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado con id: " + id));
        return enrichWithCategory(game);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameResponseDTO> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(this::enrichWithCategory)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameResponseDTO> findByActive(boolean active) {
        return gameRepository.findByActive(active)
                .stream()
                .map(this::enrichWithCategory)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameResponseDTO> findByCategoryId(Long categoryId) {
        return gameRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::enrichWithCategory)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameResponseDTO> findByDeveloper(String developer) {
        return gameRepository.findByDeveloperIgnoreCase(developer)
                .stream()
                .map(this::enrichWithCategory)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameResponseDTO> findByTitle(String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::enrichWithCategory)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GameResponseDTO update(Long id, GameRequestDTO dto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado con id: " + id));
        gameMapper.updateEntityFromDTO(dto, game);
        Game updated = gameRepository.save(game);
        log.info("Juego actualizado con id: {}", updated.getId());
        return enrichWithCategory(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new RuntimeException("Juego no encontrado con id: " + id);
        }
        gameRepository.deleteById(id);
        log.info("Juego eliminado con id: {}", id);
    }
}
