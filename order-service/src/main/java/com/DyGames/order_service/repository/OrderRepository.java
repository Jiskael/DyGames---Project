package com.DyGames.order_service.repository;

import com.DyGames.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUsuarioId(Long usuarioId);
    List<Order> findByEstado(String estado);
}