package br.com.avf.microservices.product.command.rest;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
public class CreateProductRestModel {
    @NotBlank(message = "O titulo do produto é obrigatório")
    private String title;
    @Min(value = 1, message = "O valor do produto não pode ser menor que 1")
    private BigDecimal price;
    @Min(value = 1, message = "A quantidade não pode ser menor que 1")
    @Max(value = 5, message = "A quantidade não pode ser maior que 5")
    private Integer quantity;
}
