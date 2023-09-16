package ee.ordermanagement.AssessementSpring.repositories;

import ee.ordermanagement.AssessementSpring.models.Order;
import ee.ordermanagement.AssessementSpring.repositories.interfaces.OrderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
    List<Order> findAllByDateOfSubmission(LocalDate dateOfSubmission);

    @Query(
            value = "select * from orders o where o.customer_id = :id",
            nativeQuery = true
    )
    List<Order> findOrdersByCustomerIdJpql(@Param("id") Long id);
    @Query(
            value = "select o.id, o.date_of_submission, o.customer_id from orders o " +
                    "join order_lines ol on o.id = ol.order_id " +
                    "join order_line_products olp on ol.id = olp.order_line_id where olp.product_id = :id",
            nativeQuery = true
    )
    List<Order> findOrdersByProductIdJpql(@Param("id") Long id);
}
