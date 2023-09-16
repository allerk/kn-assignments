package ee.ordermanagement.AssessementSpring.unit.service;


import ee.ordermanagement.AssessementSpring.models.Product;
import ee.ordermanagement.AssessementSpring.repositories.ProductRepository;
import ee.ordermanagement.AssessementSpring.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProductServiceTest {

    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;

    @Test
    void createProduct(){
        Product product = new Product(null, "testName", "A2000", 1500f, new ArrayList<>());
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.addProduct(product);

        Assertions.assertEquals(product.getName(), createdProduct.getName());
        verify(productRepository).save(product);

    }
}
