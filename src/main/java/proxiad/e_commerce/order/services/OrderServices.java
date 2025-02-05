package proxiad.e_commerce.order.services;

import proxiad.e_commerce.order.dto.OrderDTO;
import proxiad.e_commerce.order.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderServices {
    
    Order createOrder(Order order);

    Optional<Order> getOrderById(Long id);

    List<Order> getAllOrders();

    Order updateOrder(Long id, Order orderDetails);

    void deleteOrder(Long id);

    // Map Order entity to OrderDTO
    OrderDTO mapToDTO(Order order);

    // Map OrderDTO to Order entity
    Order mapToEntity(OrderDTO orderDTO);
}
