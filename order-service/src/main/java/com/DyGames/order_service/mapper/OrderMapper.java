package com.DyGames.order_service.mapper;

import com.DyGames.order_service.client.UserClient;
import com.DyGames.order_service.dto.OrderRespuesta;
import com.DyGames.order_service.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    @Autowired
    private UserClient userClient;

    public OrderRespuesta toDTO(Order order) {
        if (order == null) return null;
        OrderRespuesta or = new OrderRespuesta();
        or.setId(order.getId());
        or.setUsuario(userClient.buscarUsuarioPorId(order.getUsuarioId()));
        or.setTotal(order.getTotal());
        or.setEstado(order.getEstado());
        or.setCreadoEn(order.getCreadoEn());
        return or;
    }

    public List<OrderRespuesta> toDTOList(List<Order> lista) {
        return lista.stream().map(this::toDTO).toList();
    }
}
