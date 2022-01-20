package br.com.avf.microservices.product.config;

import br.com.avf.microservices.product.command.interceptor.CreateProductCommandInterceptor;
import br.com.avf.microservices.product.core.errorhandling.ProductsServiceEventsErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author angelo.vicente@veolia.com
 */
@Configuration
public class ProductConfiguration {

    @Bean
    public String registerInterceptor(ApplicationContext context, CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
        return "registered";
    }

    @Bean
    public String configure(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler("product-group", configuration -> new ProductsServiceEventsErrorHandler());
        return "configured";
    }
}
