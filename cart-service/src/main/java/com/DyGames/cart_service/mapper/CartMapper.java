package com.DyGames.cart_service.mapper;

import com.DyGames.cart_service.dto.CartRespuesta;
import com.DyGames.cart_service.model.Cart;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CartMapper {

    public CartRespuesta toDTO(Cart cart) {
        if (cart == null) return null;
        CartRespuesta cr = new CartRespuesta();
        cr.setId(cart.getId());
        cr.setUsuarioId(cart.getUsuarioId());
        cr.setJuegoId(cart.getJuegoId());
        cr.setPrecio(cart.getPrecio());
        cr.setCreadoEn(cart.getCreadoEn());
        return cr;
    }

    public List<CartRespuesta> toDTOList(List<Cart> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}