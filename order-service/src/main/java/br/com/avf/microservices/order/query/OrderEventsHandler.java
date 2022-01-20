package br.com.avf.microservices.order.query;

import br.com.avf.microservices.order.core.data.OrderEntity;
import br.com.avf.microservices.order.core.data.OrderRepository;
import br.com.avf.microservices.order.core.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author angelo.vicente@veolia.com
 */
@Component
@ProcessingGroup("order-group")
@RequiredArgsConstructor
public class OrderEventsHandler {
    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(event, entity);
        orderRepository.save(entity);

    }
}
