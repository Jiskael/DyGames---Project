package com.DyGames.library_service.repository;

import com.DyGames.library_service.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    List<Library> findByUsuarioId(Long usuarioId);
    List<Library> findByJuegoId(Long juegoId);
    boolean existsByUsuarioIdAndJuegoId(Long usuarioId, Long juegoId);
}