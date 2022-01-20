package br.com.avf.microservices.product.query.rest;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
public class ProductRestModel {
    private String productId;

    private String title;
    private BigDecimal price;
    private Integer quantity;
}
