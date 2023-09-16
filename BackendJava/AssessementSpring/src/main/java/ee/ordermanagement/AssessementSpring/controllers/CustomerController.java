package ee.ordermanagement.AssessementSpring.controllers;

import ee.ordermanagement.AssessementSpring.models.Customer;
import ee.ordermanagement.AssessementSpring.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private static final String CONTENT_TYPE = "application/json";

    @PostMapping(produces=CONTENT_TYPE)
    public Customer postCustomer(@RequestBody @Valid Customer customer){
        return customerService.addCustomer(customer);
    }

    @GetMapping(produces=CONTENT_TYPE)
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }

    @GetMapping(value = "/{id}", produces=CONTENT_TYPE)
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.findById(id);
    }

//    @DeleteMapping(value = "/{id}", produces=CONTENT_TYPE)
//    public void deleteCustomer(@PathVariable Long id) {
//        customerService.deleteCustomer(id);
//    }
//
//    @PutMapping(value = "/{id}", produces=CONTENT_TYPE)
//    public void updateCustomer(@RequestBody @Valid Customer house, Long id){
//        customerService.updateHouse(house, id);
//    }
}
