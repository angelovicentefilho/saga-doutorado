package br.com.avf.microservices.product.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author angelo.vicente@veolia.com
 */
@Entity
@Table(name = "product_lookup")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductLookupEntity implements Serializable {
    @Id
    private String productId;
    @Column(unique = true)
    private String title;
}
