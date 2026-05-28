package com.DyGames.cart_service.mapper;

import com.DyGames.cart_service.client.GameClient;
import com.DyGames.cart_service.client.UserClient;
import com.DyGames.cart_service.dto.CartRespuesta;
import com.DyGames.cart_service.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    @Autowired
    private GameClient gameClient;

    @Autowired
    private UserClient userClient;

    public CartRespuesta toDTO(Cart cart) {
        if (cart == null) return null;
        CartRespuesta cr = new CartRespuesta();
        cr.setId(cart.getId());
        cr.setUsuario(userClient.buscarUsuarioPorId(cart.getUsuarioId()));
        cr.setJuego(gameClient.buscarJuegoPorId(cart.getJuegoId()));
        cr.setPrecio(cart.getPrecio());
        cr.setCreadoEn(cart.getCreadoEn());
        return cr;
    }

    public List<CartRespuesta> toDTOList(List<Cart> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}
