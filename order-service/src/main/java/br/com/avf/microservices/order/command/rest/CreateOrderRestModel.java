package br.com.avf.microservices.order.command.rest;

import lombok.Data;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
public class CreateOrderRestModel {
    private String productId;
    private int quantity;
    private String addressId;
}
