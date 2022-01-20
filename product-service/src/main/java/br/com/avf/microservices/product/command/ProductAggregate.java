package br.com.avf.microservices.product.command;

import br.com.avf.microservices.core.commands.ReserveProductCommand;
import br.com.avf.microservices.core.events.ProductReservedEvent;
import br.com.avf.microservices.product.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author angelo.vicente@veolia.com
 */
@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;

    private String title;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate(){}

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) throws Exception {
        if (isLessThanZero(command)) {
            throw new IllegalArgumentException("O preço não pode ser menor que zero!");
        }
        if (isNullOrBlank(command)) {
            throw new IllegalArgumentException("O titulo não pode ser vazio!");
        }

        ProductCreatedEvent event = new ProductCreatedEvent();
        BeanUtils.copyProperties(command, event);
        apply(event);
    }

    @CommandHandler
    public void handle(ReserveProductCommand command) {
        if (isNoItems(command)) {
            throw new IllegalArgumentException("Numero insuficiente de itens no estoque!");
        }
        apply(ProductReservedEvent.builder()
                .orderId(command.getOrderId())
                .productId(command.getProductId())
                .quantity(command.getQuantity())
                .userId(command.getUserId())
                .build());
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.productId = event.getProductId();
        this.quantity = event.getQuantity();
        this.price = event.getPrice();
        this.title = event.getTitle();
    }

    @EventSourcingHandler
    public void on(ProductReservedEvent event) {
        this.quantity -= event.getQuantity();
    }

    private boolean isNoItems(ReserveProductCommand command) {
        return quantity < command.getQuantity();
    }

    private boolean isNullOrBlank(CreateProductCommand command) {
        return command.getTitle() == null || command.getTitle().isEmpty();
    }

    private boolean isLessThanZero(CreateProductCommand command) {
        return command.getPrice().compareTo(BigDecimal.ZERO) <= 0;
    }
}
