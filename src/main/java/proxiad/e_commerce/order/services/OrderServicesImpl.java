package proxiad.e_commerce.order.services;


import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import proxiad.e_commerce.order.dto.OrderDTO;
import proxiad.e_commerce.order.entities.Order;
import proxiad.e_commerce.order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServicesImpl implements OrderServices {

    private final OrderRepository orderRepository;

    public OrderServicesImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(Long id, Order orderDetails) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
            
            order.setCustomerName(orderDetails.getCustomerName());
            order.setProduct(orderDetails.getProduct());
            order.setQuantity(orderDetails.getQuantity());
            order.setStatus(orderDetails.getStatus());

            return orderRepository.save(order);

        } catch (OptimisticLockingFailureException ex) {
            throw new RuntimeException("Update conflict: The order was modified by another transaction.", ex);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setProduct(order.getProduct());
        dto.setQuantity(order.getQuantity());
        dto.setStatus(order.getStatus());
        return dto;
    }

    @Override
    public Order mapToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setProduct(orderDTO.getProduct());
        order.setQuantity(orderDTO.getQuantity());
        order.setStatus(orderDTO.getStatus());
        return order;
    }
}
