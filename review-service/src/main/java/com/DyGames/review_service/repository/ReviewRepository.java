package com.DyGames.review_service.repository;

import com.DyGames.review_service.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUsuarioId(Long usuarioId);
    List<Review> findByJuegoId(Long juegoId);
    List<Review> findByCalificacion(Integer calificacion);
    boolean existsByUsuarioIdAndJuegoId(Long usuarioId, Long juegoId);
}