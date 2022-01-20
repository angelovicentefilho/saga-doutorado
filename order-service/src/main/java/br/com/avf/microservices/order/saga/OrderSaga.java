package br.com.avf.microservices.order.saga;

import br.com.avf.microservices.core.commands.ReserveProductCommand;
import br.com.avf.microservices.core.events.ProductReservedEvent;
import br.com.avf.microservices.order.core.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author angelo.vicente@veolia.com
 */
@Saga
@Slf4j
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    public OrderSaga(){}

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {
        commandGateway.send(ReserveProductCommand.builder()
                .orderId(event.getOrderId())
                .productId(event.getProductId())
                .quantity(event.getQuantity())
                .userId(event.getUserId())
                .build(), new CommandCallback<ReserveProductCommand, Object>() {
                @Override
                public void onResult(CommandMessage<? extends ReserveProductCommand> commandMessage, CommandResultMessage<? extends Object> commandResultMessage) {
                    if (commandResultMessage.isExceptional()) {
                        log.info(commandMessage.getCommandName());
                        log.info(commandResultMessage.getIdentifier());
                    }
                }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        log.info("Produto reservado com a ordem '{}' e o produto '{}' " , productReservedEvent.getOrderId() , productReservedEvent.getProductId());
    }

}
