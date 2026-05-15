package com.DyGames.payment_service.repository;

import com.DyGames.payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUsuarioId(Long usuarioId);
    List<Payment> findByOrdenId(Long ordenId);
    List<Payment> findByEstado(String estado);
    List<Payment> findByMetodo(String metodo);
}