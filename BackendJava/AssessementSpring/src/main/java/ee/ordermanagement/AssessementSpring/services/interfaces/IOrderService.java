package ee.ordermanagement.AssessementSpring.services.interfaces;

import ee.ordermanagement.AssessementSpring.models.Order;
import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderDtoPost;
import ee.ordermanagement.AssessementSpring.models.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();
    Order findById(Long id);
    List<Order> findAllByDateOfSubmission(String dateOfSubmission);
    List<Order> findOrdersByCustomerIdJpql(Long id);
    List<Order> findOrdersByProductIdJpql(Long id);
    List<OrderDTO> findOrdersByCustomerIdCriteria(Long id);
    List<OrderDTO> findOrdersByProductIdCriteria(Long id);
    Order addOrder(OrderDtoPost orderDTO);
}
