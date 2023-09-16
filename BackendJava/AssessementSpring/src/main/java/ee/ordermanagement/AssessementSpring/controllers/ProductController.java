package ee.ordermanagement.AssessementSpring.controllers;

import ee.ordermanagement.AssessementSpring.models.Product;
import ee.ordermanagement.AssessementSpring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    private static final String CONTENT_TYPE = "application/json";

    @PostMapping(produces=CONTENT_TYPE)
    public Product postProduct(@RequestBody @Valid Product product){
        return productService.addProduct(product);
    }

    @GetMapping(produces=CONTENT_TYPE)
    public List<Product> getProducts(){
        return productService.findAll();
    }

    @GetMapping(value = "/{id}", produces=CONTENT_TYPE)
    public Product getProductById(@PathVariable Long id){
        return productService.findById(id);
    }
}
