package com.DyGames.review_service.mapper;

import com.DyGames.review_service.client.GameClient;
import com.DyGames.review_service.client.UserClient;
import com.DyGames.review_service.dto.ReviewRespuesta;
import com.DyGames.review_service.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {

    @Autowired
    private UserClient userClient;

    @Autowired
    private GameClient gameClient;

    public ReviewRespuesta toDTO(Review review) {
        if (review == null) return null;
        ReviewRespuesta rr = new ReviewRespuesta();
        rr.setId(review.getId());
        rr.setUsuario(userClient.buscarUsuarioPorId(review.getUsuarioId()));
        rr.setJuego(gameClient.buscarJuegoPorId(review.getJuegoId()));
        rr.setCalificacion(review.getCalificacion());
        rr.setComentario(review.getComentario());
        rr.setCreadoEn(review.getCreadoEn());
        return rr;
    }

    public List<ReviewRespuesta> toDTOList(List<Review> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}
