package ee.ordermanagement.AssessementSpring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "order_lines")
@SequenceGenerator(name = "order_lines_sequence_generator", sequenceName = "order_lines_sequence")
public class OrderLine {

    @Id
    @GeneratedValue(generator = "order_lines_sequence_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @OneToMany(mappedBy = "orderLine", fetch = FetchType.LAZY)
    private List<OrderLineProduct> orderLineProducts;
}
