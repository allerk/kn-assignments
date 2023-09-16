package ee.ordermanagement.AssessementSpring.services.interfaces;

import ee.ordermanagement.AssessementSpring.models.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer addCustomer(Customer customer);
}
