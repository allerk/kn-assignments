package ee.ordermanagement.AssessementSpring.controllers;

import ee.ordermanagement.AssessementSpring.models.Order;
import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderDtoPost;
import ee.ordermanagement.AssessementSpring.models.dto.OrderDTO;
import ee.ordermanagement.AssessementSpring.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private static final String CONTENT_TYPE = "application/json";

    @PostMapping(produces=CONTENT_TYPE)
    public Order postOrder(@RequestBody @Valid OrderDtoPost orderDTO){
        return orderService.addOrder(orderDTO);
    }

    @GetMapping(produces=CONTENT_TYPE)
    public List<Order> getOrders(){
        return orderService.findAll();
    }

    @GetMapping(value = "/{id}", produces=CONTENT_TYPE)
    public Order getOrderById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @GetMapping(value = "/date/{dateOfSubmission}", produces=CONTENT_TYPE)
    public List<Order> getAllByDateOfSubmission(@PathVariable String dateOfSubmission){
        return orderService.findAllByDateOfSubmission(dateOfSubmission);
    }

    @GetMapping(value = "/productId/jpql/{id}", produces=CONTENT_TYPE)
    public List<Order> findOrdersByProductIdJpql(@PathVariable Long id){
        return orderService.findOrdersByProductIdJpql(id);
    }

    @GetMapping(value = "/productId/criteria/{id}", produces=CONTENT_TYPE)
    public List<OrderDTO> findOrdersByProductIdCriteria(@PathVariable Long id){
        return orderService.findOrdersByProductIdCriteria(id);
    }

    @GetMapping(value = "/customerId/jpql/{id}", produces=CONTENT_TYPE)
    public List<Order> findOrdersByCustomerIdJpql(@PathVariable Long id){
        return orderService.findOrdersByCustomerIdJpql(id);
    }

    @GetMapping(value = "/customerId/criteria/{id}", produces=CONTENT_TYPE)
    public List<OrderDTO> findOrdersByCustomerIdCriteria(@PathVariable Long id){
        return orderService.findOrdersByCustomerIdCriteria(id);
    }
}
