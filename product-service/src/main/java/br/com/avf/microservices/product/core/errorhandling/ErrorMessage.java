package br.com.avf.microservices.product.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author angelo.vicente@veolia.com
 */
@Data
@AllArgsConstructor
public class ErrorMessage {
    private final Date timestamp;
    private final String message;
}
