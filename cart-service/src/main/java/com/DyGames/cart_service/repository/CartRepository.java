package com.DyGames.cart_service.repository;

import com.DyGames.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndJuegoId(Long usuarioId, Long juegoId);
    void deleteByUsuarioId(Long usuarioId);
}