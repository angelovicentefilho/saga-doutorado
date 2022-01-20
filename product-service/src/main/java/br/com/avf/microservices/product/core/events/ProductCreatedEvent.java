package br.com.avf.microservices.product.core.events;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
public class ProductCreatedEvent {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
