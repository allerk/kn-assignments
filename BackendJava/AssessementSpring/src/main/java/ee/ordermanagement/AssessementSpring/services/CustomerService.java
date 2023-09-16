package ee.ordermanagement.AssessementSpring.services;

import ee.ordermanagement.AssessementSpring.models.Customer;
import ee.ordermanagement.AssessementSpring.repositories.CustomerRepository;
import ee.ordermanagement.AssessementSpring.services.interfaces.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        if (customerRepository.findById(id).isPresent()){
            return customerRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
