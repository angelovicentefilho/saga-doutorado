package br.com.avf.microservices.core.events;

import lombok.Builder;
import lombok.Data;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
@Builder
public class ProductReservedEvent {
    private final String productId;
    private final int quantity;
    private final String orderId;
    private final String userId;
}
