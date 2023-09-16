package ee.ordermanagement.AssessementSpring.models.dto;

import ee.ordermanagement.AssessementSpring.models.OrderLineProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderLineDTO {

    private Long id;

    private Integer quantity;
    private Long order_id;

    private List<OrderLineProduct> orderLineProducts;
}
