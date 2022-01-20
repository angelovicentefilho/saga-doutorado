package br.com.avf.microservices.product.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

/**
 * @author angelo.vicente@veolia.com
 */
@Builder
@Data
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String productId;

    private final String title;
    private final BigDecimal price;
    private final Integer quantity;

}
