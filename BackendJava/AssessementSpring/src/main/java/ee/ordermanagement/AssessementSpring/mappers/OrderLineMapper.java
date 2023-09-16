package ee.ordermanagement.AssessementSpring.mappers;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineDtoPost;
import ee.ordermanagement.AssessementSpring.models.OrderLine;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderLineMapper {

    private final ModelMapper modelMapper;

    public OrderLine orderLineDtoPostToOrderLine(OrderLineDtoPost orderLineDTO){
        return modelMapper.map(orderLineDTO, OrderLine.class);
    }
}
