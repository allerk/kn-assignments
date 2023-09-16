package ee.ordermanagement.AssessementSpring.controllers.dto.post;

import lombok.Data;

@Data
public class OrderLineProductDtoPost {

    private Long order_line_id;

    private Long product_id;
}
