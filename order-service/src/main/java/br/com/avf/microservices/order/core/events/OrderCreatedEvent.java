package br.com.avf.microservices.order.core.events;

import br.com.avf.microservices.order.utility.OrderStatus;
import lombok.Data;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
