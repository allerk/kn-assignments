package ee.ordermanagement.AssessementSpring.services;

import ee.ordermanagement.AssessementSpring.mappers.OrderMapper;
import ee.ordermanagement.AssessementSpring.models.Customer;
import ee.ordermanagement.AssessementSpring.models.Order;
import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderDtoPost;
import ee.ordermanagement.AssessementSpring.models.dto.OrderDTO;
import ee.ordermanagement.AssessementSpring.repositories.CustomerRepository;
import ee.ordermanagement.AssessementSpring.repositories.OrderRepository;
import ee.ordermanagement.AssessementSpring.services.interfaces.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Order> findAll() {
         return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        if (orderRepository.findById(id).isPresent()){
            return orderRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public List<Order> findAllByDateOfSubmission(String dateOfSubmission) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateOfSubmission, formatter);

        return orderRepository.findAllByDateOfSubmission(localDate);
    }

    @Override
    public List<Order> findOrdersByCustomerIdJpql(Long id) {
        return orderRepository.findOrdersByCustomerIdJpql(id);
    }

    @Override
    public List<Order> findOrdersByProductIdJpql(Long id) {
        return orderRepository.findOrdersByProductIdJpql(id);
    }

    @Override
    public List<OrderDTO> findOrdersByCustomerIdCriteria(Long id) {
        return orderRepository.findOrdersByCustomerIdCriteria(id);
    }

    @Override
    public List<OrderDTO> findOrdersByProductIdCriteria(Long id) {
        return orderRepository.findOrdersByProductIdCriteria(id);
    }

    @Override
    @Transactional
    public Order addOrder(OrderDtoPost orderDTO) {
        OrderMapper mapper = new OrderMapper(modelMapper);
        Order order = mapper.orderDtoPostToOrder(orderDTO);
        Customer customer = customerRepository.findById(orderDTO.getCustomer_id()).get();
        order.setCustomer(customer);
        return orderRepository.save(order);
    }
}
