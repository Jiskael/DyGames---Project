package com.DyGames.order_service.service;

import com.DyGames.order_service.client.CartClient;
import com.DyGames.order_service.client.LibraryClient;
import com.DyGames.order_service.dto.LibraryRequest;
import com.DyGames.order_service.dto.OrderRespuesta;
import com.DyGames.order_service.mapper.OrderMapper;
import com.DyGames.order_service.model.Order;
import com.DyGames.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

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

    // Aqui creamos el orden desde el carrito
    public Order crearOrden(Long usuarioId) {
        // hacemos que obtenga el carrito del usuario por Feign
        List<Object> carrito = cartClient.obtenerCarrito(usuarioId);

        if (carrito == null || carrito.isEmpty()) {
            throw new RuntimeException("El carrito esta vacio");
        }

        // Aqui calcula el total sumando los precios del carrito
        double total = 0.0;
        for (Object item : carrito) {
            Map<String, Object> itemMap = (Map<String, Object>) item;
            total += ((Number) itemMap.get("precio")).doubleValue();
        }

        // aqui pues guarda la orden
        Order orden = new Order();
        orden.setUsuarioId(usuarioId);
        orden.setTotal(total);
        orden.setEstado("PAGADO");
        Order ordenGuardada = orderRepository.save(orden);

        // Registramos cada juego en la biblioteca por Feign
        for (Object item : carrito) {
            Map<String, Object> itemMap = (Map<String, Object>) item;
            Long juegoId = ((Number) itemMap.get("juegoId")).longValue();
            libraryClient.agregarABiblioteca(
                    new LibraryRequest(usuarioId, juegoId, ordenGuardada.getId())
            );
        }

        // Vaciamos el carrito por Feign
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

    //Y despues el copy paste de los metodos dto de las demas clases adaptado a esta
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