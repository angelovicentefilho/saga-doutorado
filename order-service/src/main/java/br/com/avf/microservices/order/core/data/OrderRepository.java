package br.com.avf.microservices.order.core.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author angelo.vicente@veolia.com
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

}
