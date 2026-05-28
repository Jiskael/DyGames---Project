package com.DyGames.payment_service.mapper;

import com.DyGames.payment_service.client.OrderClient;
import com.DyGames.payment_service.client.UserClient;
import com.DyGames.payment_service.dto.PaymentRespuesta;
import com.DyGames.payment_service.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapper {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderClient orderClient;

    public PaymentRespuesta toDTO(Payment payment) {
        if (payment == null) return null;
        PaymentRespuesta pr = new PaymentRespuesta();
        pr.setId(payment.getId());
        pr.setOrden(orderClient.buscarOrdenPorId(payment.getOrdenId()));
        pr.setUsuario(userClient.buscarUsuarioPorId(payment.getUsuarioId()));
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
