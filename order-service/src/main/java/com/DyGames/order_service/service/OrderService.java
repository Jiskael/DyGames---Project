package com.DyGames.order_service.service;

import com.DyGames.order_service.client.CartClient;
import com.DyGames.order_service.client.LibraryClient;
import com.DyGames.order_service.dto.CartRespuesta;
import com.DyGames.order_service.dto.LibraryRequest;
import com.DyGames.order_service.dto.OrderRespuesta;
import com.DyGames.order_service.mapper.OrderMapper;
import com.DyGames.order_service.model.Order;
import com.DyGames.order_service.repository.OrderRepository;
import com.DyGames.order_service.exception.CarritoVacioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private LibraryClient libraryClient;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order crearOrden(Long usuarioId) {
        // Obtiene el carrito tipado via Feign
        List<CartRespuesta> carrito = cartClient.obtenerCarrito(usuarioId);

        if (carrito == null || carrito.isEmpty()) {
            throw new CarritoVacioException("El carrito del usuario esta vacio");
        }

        // Calcula el total sumando precios del carrito
        double total = carrito.stream()
                .mapToDouble(CartRespuesta::getPrecio)
                .sum();

        // Guarda la orden
        Order orden = new Order();
        orden.setUsuarioId(usuarioId);
        orden.setTotal(total);
        orden.setEstado("PAGADO");
        Order ordenGuardada = orderRepository.save(orden);

        // Registra cada juego en la biblioteca via Feign
        for (CartRespuesta item : carrito) {
            libraryClient.agregarABiblioteca(
                    new LibraryRequest(usuarioId, item.getJuegoId(), ordenGuardada.getId())
            );
        }

        // Vacía el carrito via Feign
        cartClient.vaciarCarrito(usuarioId);

        return ordenGuardada;
    }

    public Order update(Long id, Order order) {
        Order o = orderRepository.findById(id).orElse(null);
        if (o == null) return null;
        o.setEstado(order.getEstado());
        return orderRepository.save(o);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    // Metodos DTO
    public OrderRespuesta findDTO(Long id) {
        return orderMapper.toDTO(findById(id));
    }

    public List<OrderRespuesta> findDTOList() {
        return orderMapper.toDTOList(findAll());
    }

    // Reportes
    public List<OrderRespuesta> findByUsuario(Long usuarioId) {
        return orderMapper.toDTOList(orderRepository.findByUsuarioId(usuarioId));
    }

    public List<OrderRespuesta> findByEstado(String estado) {
        return orderMapper.toDTOList(orderRepository.findByEstado(estado));
    }
}
