package ee.ordermanagement.AssessementSpring.services;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineDtoPost;
import ee.ordermanagement.AssessementSpring.mappers.OrderLineMapper;
import ee.ordermanagement.AssessementSpring.models.Order;
import ee.ordermanagement.AssessementSpring.models.OrderLine;
import ee.ordermanagement.AssessementSpring.repositories.OrderLineRepository;
import ee.ordermanagement.AssessementSpring.repositories.OrderRepository;
import ee.ordermanagement.AssessementSpring.services.interfaces.IOrderLineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderLineService implements IOrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    @Override
    public OrderLine findById(Long id) {
        if (orderLineRepository.findById(id).isPresent()){
            return orderLineRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public OrderLine addOrderLine(OrderLineDtoPost orderLineDTO) {
        OrderLineMapper mapper = new OrderLineMapper(modelMapper);
        OrderLine orderLine = mapper.orderLineDtoPostToOrderLine(orderLineDTO);
        Order order = orderRepository.findById(orderLineDTO.getOrder_id()).get();
        orderLine.setOrder(order);
        return orderLineRepository.save(orderLine);
    }
}
