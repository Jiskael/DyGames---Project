package com.DyGames.review_service.mapper;

import com.DyGames.review_service.dto.ReviewRespuesta;
import com.DyGames.review_service.model.Review;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ReviewMapper {

    public ReviewRespuesta toDTO(Review review) {
        if (review == null) return null;
        ReviewRespuesta rr = new ReviewRespuesta();
        rr.setId(review.getId());
        rr.setUsuarioId(review.getUsuarioId());
        rr.setJuegoId(review.getJuegoId());
        rr.setCalificacion(review.getCalificacion());
        rr.setComentario(review.getComentario());
        rr.setCreadoEn(review.getCreadoEn());
        return rr;
    }

    public List<ReviewRespuesta> toDTOList(List<Review> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}