package ee.ordermanagement.AssessementSpring.mappers;

import ee.ordermanagement.AssessementSpring.models.Order;
import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderDtoPost;
import ee.ordermanagement.AssessementSpring.models.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderMapper {

    private final ModelMapper modelMapper;

    public Order orderDtoPostToOrder(OrderDtoPost orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }

    public OrderDTO orderToOrderDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }
}
