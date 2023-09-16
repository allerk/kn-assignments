package ee.ordermanagement.AssessementSpring.services;

import ee.ordermanagement.AssessementSpring.models.Product;
import ee.ordermanagement.AssessementSpring.repositories.ProductRepository;
import ee.ordermanagement.AssessementSpring.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        if (productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
