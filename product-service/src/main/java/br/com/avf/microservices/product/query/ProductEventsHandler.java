package br.com.avf.microservices.product.query;

import br.com.avf.microservices.core.events.ProductReservedEvent;
import br.com.avf.microservices.product.core.data.ProductEntity;
import br.com.avf.microservices.product.core.data.ProductRepository;
import br.com.avf.microservices.product.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author angelo.vicente@veolia.com
 */
@Component
@ProcessingGroup(value = "product-group")
@RequiredArgsConstructor
@Slf4j
public class ProductEventsHandler {

    private final ProductRepository repository;

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException e) {
        log.error("Argumento inválido", e);
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception e) {
        log.error("Erro: ", e);
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(event, entity);
        repository.save(entity);
    }

    @EventHandler
    public void on(ProductReservedEvent event) {
        ProductEntity entity = this.repository.findByProductId(event.getProductId());
        entity.setQuantity(entity.getQuantity() - event.getQuantity());
        this.repository.save(entity);
        log.info("O produto '{}', foi reservado com sucesso!, Pela ordem '{}'", event.getProductId(), event.getOrderId());
    }
}
