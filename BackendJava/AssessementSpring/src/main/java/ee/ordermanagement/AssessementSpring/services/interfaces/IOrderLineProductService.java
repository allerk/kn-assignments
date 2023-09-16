package ee.ordermanagement.AssessementSpring.services.interfaces;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineProductDtoPost;
import ee.ordermanagement.AssessementSpring.models.OrderLineProduct;

import java.util.List;

public interface IOrderLineProductService {
    List<OrderLineProduct> findAll();
    OrderLineProduct findById(Long id);
    OrderLineProduct addOrderLineProduct(OrderLineProductDtoPost orderLineProductDTO);
}
