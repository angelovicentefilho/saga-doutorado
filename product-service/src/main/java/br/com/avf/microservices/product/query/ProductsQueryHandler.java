package br.com.avf.microservices.product.query;

import br.com.avf.microservices.product.core.data.ProductEntity;
import br.com.avf.microservices.product.core.data.ProductRepository;
import br.com.avf.microservices.product.query.rest.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author angelo.vicente@veolia.com
 */
@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {
    private final ProductRepository repository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query) {
        List<ProductRestModel> productRestModels = newArrayList();
        List<ProductEntity> storedProducts = this.repository.findAll();
        storedProducts.forEach( product -> {
            ProductRestModel model = new ProductRestModel();
            BeanUtils.copyProperties(product, model);
            productRestModels.add(model);
        });
        return productRestModels;
    }
}
