package br.com.avf.microservices.order.core.data;

import br.com.avf.microservices.order.utility.OrderStatus;
import lombok.Data;

import javax.persistence.*;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(unique = true)
    public String orderId;
    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
