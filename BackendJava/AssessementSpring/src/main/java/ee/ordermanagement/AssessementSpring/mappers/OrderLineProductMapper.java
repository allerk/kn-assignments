package ee.ordermanagement.AssessementSpring.mappers;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineProductDtoPost;
import ee.ordermanagement.AssessementSpring.models.OrderLineProduct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderLineProductMapper {

    private final ModelMapper modelMapper;

    public OrderLineProduct orderLineProductDtoPostToOrderLineProduct(OrderLineProductDtoPost orderLineProductDTO){
        return modelMapper.map(orderLineProductDTO, OrderLineProduct.class);
    }
}
