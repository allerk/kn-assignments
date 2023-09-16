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
@Table(name = "customers")
@SequenceGenerator(name = "customers_sequence_generator", sequenceName = "customers_sequence")
public class Customer {

    @Id
    @GeneratedValue(generator = "customers_sequence_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "registration_code", nullable = false)
    private String registrationCode;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    @JsonIgnore
    private List<Order> orders;
}
