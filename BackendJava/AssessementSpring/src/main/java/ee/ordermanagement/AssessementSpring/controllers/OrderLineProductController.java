package ee.ordermanagement.AssessementSpring.controllers;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineProductDtoPost;
import ee.ordermanagement.AssessementSpring.models.OrderLineProduct;
import ee.ordermanagement.AssessementSpring.services.OrderLineProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orderlineproducts")
public class OrderLineProductController {

    @Autowired
    private OrderLineProductService orderLineProductService;

    private static final String CONTENT_TYPE = "application/json";

    @PostMapping(produces=CONTENT_TYPE)
    public OrderLineProduct postOrderLineProduct(@RequestBody @Valid OrderLineProductDtoPost orderLineProductDTO){
        return orderLineProductService.addOrderLineProduct(orderLineProductDTO);
    }

    @GetMapping(produces=CONTENT_TYPE)
    public List<OrderLineProduct> getOrderLineProducts(){
        return orderLineProductService.findAll();
    }

    @GetMapping(value = "/{id}", produces=CONTENT_TYPE)
    public OrderLineProduct getOrderLineProductById(@PathVariable Long id){
        return orderLineProductService.findById(id);
    }
}
