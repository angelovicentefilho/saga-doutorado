package br.com.avf.microservices.product.command.rest;

import br.com.avf.microservices.product.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author angelo.vicente@veolia.com
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class CreateProductController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String create(@Valid @RequestBody CreateProductRestModel product) {
        return commandGateway.sendAndWait(CreateProductCommand.builder()
                .price(product.getPrice())
                .productId(UUID.randomUUID().toString().toUpperCase())
                .title(product.getTitle())
                .quantity(product.getQuantity())
                .build());
    }
}
