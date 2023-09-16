package ee.ordermanagement.AssessementSpring.repositories.interfaces;

import ee.ordermanagement.AssessementSpring.models.dto.OrderDTO;

import java.util.List;

public interface OrderRepositoryCustom {

    List<OrderDTO> findOrdersByCustomerIdCriteria(Long id);
    List<OrderDTO> findOrdersByProductIdCriteria(Long id);
}
