package com.DyGames.payment_service.mapper;

import com.DyGames.payment_service.dto.PaymentRespuesta;
import com.DyGames.payment_service.model.Payment;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PaymentMapper {

    public PaymentRespuesta toDTO(Payment payment) {
        if (payment == null) return null;
        PaymentRespuesta pr = new PaymentRespuesta();
        pr.setId(payment.getId());
        pr.setOrdenId(payment.getOrdenId());
        pr.setUsuarioId(payment.getUsuarioId());
        pr.setMonto(payment.getMonto());
        pr.setMetodo(payment.getMetodo());
        pr.setEstado(payment.getEstado());
        pr.setCreadoEn(payment.getCreadoEn());
        return pr;
    }

    public List<PaymentRespuesta> toDTOList(List<Payment> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}