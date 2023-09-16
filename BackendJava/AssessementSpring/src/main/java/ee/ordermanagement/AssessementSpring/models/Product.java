package ee.ordermanagement.AssessementSpring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "products")
@SequenceGenerator(name = "products_sequence_generator", sequenceName = "products_sequence")
public class Product {

    @Id
    @GeneratedValue(generator = "products_sequence_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sku_code", nullable = false)
    private String skuCode;

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderLineProduct> orderLineProducts;
}
