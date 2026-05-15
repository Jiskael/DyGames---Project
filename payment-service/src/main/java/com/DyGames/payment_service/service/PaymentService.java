package com.DyGames.payment_service.service;

import com.DyGames.payment_service.dto.PaymentRespuesta;
import com.DyGames.payment_service.mapper.PaymentMapper;
import com.DyGames.payment_service.model.Payment;
import com.DyGames.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment update(Long id, Payment payment) {
        Payment p = paymentRepository.findById(id).orElse(null);
        if (p == null) return null;
        p.setEstado(payment.getEstado());
        p.setMetodo(payment.getMetodo());
        p.setMonto(payment.getMonto());
        return paymentRepository.save(p);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    // Metodos DTO
    public PaymentRespuesta findDTO(Long id) {
        return paymentMapper.toDTO(findById(id));
    }

    public List<PaymentRespuesta> findDTOList() {
        return paymentMapper.toDTOList(findAll());
    }

    // Reportes
    public List<PaymentRespuesta> findByUsuario(Long usuarioId) {
        return paymentMapper.toDTOList(paymentRepository.findByUsuarioId(usuarioId));
    }

    public List<PaymentRespuesta> findByOrden(Long ordenId) {
        return paymentMapper.toDTOList(paymentRepository.findByOrdenId(ordenId));
    }

    public List<PaymentRespuesta> findByEstado(String estado) {
        return paymentMapper.toDTOList(paymentRepository.findByEstado(estado));
    }

    public List<PaymentRespuesta> findByMetodo(String metodo) {
        return paymentMapper.toDTOList(paymentRepository.findByMetodo(metodo));
    }
}