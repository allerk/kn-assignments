package ee.ordermanagement.AssessementSpring.services.interfaces;

import ee.ordermanagement.AssessementSpring.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> findAll();
    Product findById(Long id);
    Product addProduct(Product product);
}
