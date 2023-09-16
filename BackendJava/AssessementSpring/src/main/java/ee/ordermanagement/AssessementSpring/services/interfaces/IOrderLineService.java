package ee.ordermanagement.AssessementSpring.services.interfaces;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineDtoPost;
import ee.ordermanagement.AssessementSpring.models.OrderLine;

import java.util.List;

public interface IOrderLineService {
    List<OrderLine> findAll();
    OrderLine findById(Long id);
    OrderLine addOrderLine(OrderLineDtoPost orderLineDTO);
}
