package proxiad.e_commerce.order.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import proxiad.e_commerce.order.dto.OrderDTO;
import proxiad.e_commerce.order.entities.Order;
import proxiad.e_commerce.order.services.OrderServices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order API", description = "Endpoints for managing orders")
@Transactional
public class OrderAPI {

    private final OrderServices orderServices;

    public OrderAPI(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CAN_WRITE')")
    @Operation(summary = "Create a new order", description = "Saves a new order in the database")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderServices.mapToEntity(orderDTO);
        Order savedOrder = orderServices.createOrder(order);
        return ResponseEntity.ok(orderServices.mapToDTO(savedOrder));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CAN_WRITE') || hasRole('ROLE_CAN_READ')")
    @Operation(summary = "Get order by ID", description = "Retrieve an order using its ID")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderServices.getOrderById(id);
        return order.map(o -> ResponseEntity.ok(orderServices.mapToDTO(o)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CAN_WRITE') || hasRole('ROLE_CAN_READ')")
    @Operation(summary = "Get all orders", description = "Retrieve all orders in the system")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderServices.getAllOrders();
        List<OrderDTO> orderDTOs = orders.stream()
                                         .map(orderServices::mapToDTO)
                                         .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CAN_WRITE')")
    @Operation(summary = "Update an order", description = "Modify an existing order by ID")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = orderServices.mapToEntity(orderDTO);
        Order updatedOrder = orderServices.updateOrder(id, order);
        return ResponseEntity.ok(orderServices.mapToDTO(updatedOrder));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CAN_DELETE')")
    @Operation(summary = "Delete an order", description = "Remove an order using its ID")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderServices.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
