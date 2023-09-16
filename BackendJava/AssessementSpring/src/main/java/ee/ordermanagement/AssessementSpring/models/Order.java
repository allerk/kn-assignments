package ee.ordermanagement.AssessementSpring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode

@Entity
@Table(name = "orders")
@SequenceGenerator(name = "orders_sequence_generator", sequenceName = "orders_sequence")
public class Order {

    @Id
    @GeneratedValue(generator = "orders_sequence_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date_of_submission", nullable = false)
    private LocalDate dateOfSubmission;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderLine> orderLines;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
}
