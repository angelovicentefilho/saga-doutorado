package br.com.avf.microservices.order.command;

import br.com.avf.microservices.order.core.events.OrderCreatedEvent;
import br.com.avf.microservices.order.utility.OrderStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author angelo.vicente@veolia.com
 */
@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    public String orderId;
    private String userId;
    private String productId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
    public OrderAggregate(){}

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {

        if (command.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantidade não pode ser menor que zero");
        }
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
        BeanUtils.copyProperties(command, orderCreatedEvent);
        apply(orderCreatedEvent);

    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.userId = event.getUserId();
        this.productId = event.getProductId();
        this.quantity = event.getQuantity();
        this.addressId = event.getAddressId();
        this.orderStatus = event.getOrderStatus();
    }
}
