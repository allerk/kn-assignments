package ee.ordermanagement.AssessementSpring.controllers.dto.post;

import lombok.Data;

@Data
public class OrderLineDtoPost {

    private Integer quantity;

    private Long order_id;
}
