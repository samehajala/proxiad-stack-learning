package proxiad.e_commerce.order.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import proxiad.e_commerce.order.dto.OrderDTO;
import proxiad.e_commerce.order.entities.Order;
import proxiad.e_commerce.order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServicesImpl implements OrderServices {

    private final OrderRepository orderRepository;

    public OrderServicesImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order updateOrder(Long id, Order orderDetails) {
        return orderRepository.findById(id).map(order -> {
            order.setCustomerName(orderDetails.getCustomerName());
            order.setStatus(orderDetails.getStatus());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
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
    @Transactional
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
