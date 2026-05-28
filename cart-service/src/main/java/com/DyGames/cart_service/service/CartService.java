package com.DyGames.cart_service.service;

import com.DyGames.cart_service.client.GameClient;
import com.DyGames.cart_service.dto.CartRespuesta;
import com.DyGames.cart_service.dto.GameRespuesta;
import com.DyGames.cart_service.mapper.CartMapper;
import com.DyGames.cart_service.model.Cart;
import com.DyGames.cart_service.repository.CartRepository;
import com.DyGames.cart_service.exception.JuegoNoExisteException;
import com.DyGames.cart_service.exception.JuegoYaEnCarritoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GameClient gameClient;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart save(Cart cart) {
        // Verifica que el juego existe en game-service via Feign (ahora tipado)
        GameRespuesta juego = gameClient.buscarJuegoPorId(cart.getJuegoId());
        if (juego == null) {
            throw new JuegoNoExisteException("El juego no existe en el sistema");
        }

        // Verifica que el usuario no tenga ya ese juego en el carrito
        if (cartRepository.existsByUsuarioIdAndJuegoId(
                cart.getUsuarioId(), cart.getJuegoId())) {
            throw new JuegoYaEnCarritoException("El juego ya esta en el carrito del usuario");
        }

        return cartRepository.save(cart);
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    public void vaciarCarrito(Long usuarioId) {
        cartRepository.deleteByUsuarioId(usuarioId);
    }

    // Metodos DTO
    public CartRespuesta findDTO(Long id) {
        return cartMapper.toDTO(findById(id));
    }

    public List<CartRespuesta> findDTOList() {
        return cartMapper.toDTOList(findAll());
    }

    // Reportes
    public List<CartRespuesta> findByUsuario(Long usuarioId) {
        return cartMapper.toDTOList(cartRepository.findByUsuarioId(usuarioId));
    }
}
