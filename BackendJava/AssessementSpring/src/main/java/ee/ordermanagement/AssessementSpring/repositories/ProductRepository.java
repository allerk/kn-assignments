package ee.ordermanagement.AssessementSpring.repositories;

import ee.ordermanagement.AssessementSpring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
