package ee.ordermanagement.AssessementSpring.repositories;

import ee.ordermanagement.AssessementSpring.models.OrderLineProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineProductRepository extends JpaRepository<OrderLineProduct, Long> {
}
