package com.DyGames.review_service.service;

import com.DyGames.review_service.dto.ReviewRespuesta;
import com.DyGames.review_service.mapper.ReviewMapper;
import com.DyGames.review_service.model.Review;
import com.DyGames.review_service.repository.ReviewRepository;
import com.DyGames.review_service.exception.ResenaYaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review save(Review review) {
        if (reviewRepository.existsByUsuarioIdAndJuegoId(
                review.getUsuarioId(), review.getJuegoId())) {
            throw new ResenaYaExisteException("El usuario ya hizo una resena de este juego");
        }
        return reviewRepository.save(review);
    }

    public Review update(Long id, Review review) {
        Review r = reviewRepository.findById(id).orElse(null);
        if (r == null) return null;
        r.setCalificacion(review.getCalificacion());
        r.setComentario(review.getComentario());
        return reviewRepository.save(r);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    // Metodos DTO
    public ReviewRespuesta findDTO(Long id) {
        return reviewMapper.toDTO(findById(id));
    }

    public List<ReviewRespuesta> findDTOList() {
        return reviewMapper.toDTOList(findAll());
    }

    // Reportes
    public List<ReviewRespuesta> findByUsuario(Long usuarioId) {
        return reviewMapper.toDTOList(reviewRepository.findByUsuarioId(usuarioId));
    }

    public List<ReviewRespuesta> findByJuego(Long juegoId) {
        return reviewMapper.toDTOList(reviewRepository.findByJuegoId(juegoId));
    }

    public List<ReviewRespuesta> findByCalificacion(Integer calificacion) {
        return reviewMapper.toDTOList(reviewRepository.findByCalificacion(calificacion));
    }
}