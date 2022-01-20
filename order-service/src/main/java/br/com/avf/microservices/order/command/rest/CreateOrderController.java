package br.com.avf.microservices.order.command.rest;

import br.com.avf.microservices.order.command.CreateOrderCommand;
import br.com.avf.microservices.order.utility.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author angelo.vicente@veolia.com
 */
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class CreateOrderController {
    private final CommandGateway commandGateway;

    @PostMapping
    public String save(@RequestBody CreateOrderRestModel model) {
       return commandGateway.sendAndWait(CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString().toUpperCase())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .productId(model.getProductId())
                .quantity(model.getQuantity())
                .addressId(model.getAddressId())
                .orderStatus(OrderStatus.CREATED).build());
    }
}
