package proxiad.e_commerce.order.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders") 
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String product;
    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    private String status;
 
    
    public Order() {}

    public Order(String customerName, String product, int quantity, Date orderDate, String status) {
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.status = status;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // toString() method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }
}
