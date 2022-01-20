package br.com.avf.microservices.product.command;

import br.com.avf.microservices.product.core.data.ProductLookupEntity;
import br.com.avf.microservices.product.core.data.ProductLookupRepository;
import br.com.avf.microservices.product.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * @author angelo.vicente@veolia.com
 */
@Component
@ProcessingGroup(value = "product-group")
@RequiredArgsConstructor
public class ProductLookupEventsHandler {

    private final ProductLookupRepository repository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity entity = new ProductLookupEntity(event.getProductId(), event.getTitle());
        this.repository.save(entity);
    }
}
