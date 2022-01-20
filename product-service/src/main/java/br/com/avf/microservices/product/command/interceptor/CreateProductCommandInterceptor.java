package br.com.avf.microservices.product.command.interceptor;

import br.com.avf.microservices.product.command.CreateProductCommand;
import br.com.avf.microservices.product.core.data.ProductLookupEntity;
import br.com.avf.microservices.product.core.data.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author angelo.vicente@veolia.com
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository repository;

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            log.info("Interceptor command '{}'", command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                ProductLookupEntity productLookupEntity = repository.findByProductIdOrTitle(createProductCommand.getProductId(),
                        createProductCommand.getTitle());

                if(productLookupEntity != null)
                    throw new IllegalStateException(String.format("O Produto com o id %s ou o titulo %s já existe",
                            createProductCommand.getProductId(), createProductCommand.getTitle()));
            }
            return command;
        };
    }
}
