package ee.ordermanagement.AssessementSpring.repositories;

import ee.ordermanagement.AssessementSpring.models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
