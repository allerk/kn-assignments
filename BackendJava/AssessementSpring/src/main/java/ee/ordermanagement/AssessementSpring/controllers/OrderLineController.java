package ee.ordermanagement.AssessementSpring.controllers;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineDtoPost;
import ee.ordermanagement.AssessementSpring.models.OrderLine;
import ee.ordermanagement.AssessementSpring.services.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orderlines")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    private static final String CONTENT_TYPE = "application/json";

    @PostMapping(produces=CONTENT_TYPE)
    public OrderLine postOrderLine(@RequestBody @Valid OrderLineDtoPost orderLineDTO){
        return orderLineService.addOrderLine(orderLineDTO);
    }

    @GetMapping(produces=CONTENT_TYPE)
    public List<OrderLine> getOrderLines(){
        return orderLineService.findAll();
    }

    @GetMapping(value = "/{id}", produces=CONTENT_TYPE)
    public OrderLine getOrderLineById(@PathVariable Long id){
        return orderLineService.findById(id);
    }
}
