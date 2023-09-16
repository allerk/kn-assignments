package ee.ordermanagement.AssessementSpring.services;

import ee.ordermanagement.AssessementSpring.controllers.dto.post.OrderLineProductDtoPost;
import ee.ordermanagement.AssessementSpring.mappers.OrderLineProductMapper;
import ee.ordermanagement.AssessementSpring.models.OrderLine;
import ee.ordermanagement.AssessementSpring.models.OrderLineProduct;
import ee.ordermanagement.AssessementSpring.models.Product;
import ee.ordermanagement.AssessementSpring.repositories.OrderLineProductRepository;
import ee.ordermanagement.AssessementSpring.repositories.OrderLineRepository;
import ee.ordermanagement.AssessementSpring.repositories.ProductRepository;
import ee.ordermanagement.AssessementSpring.services.interfaces.IOrderLineProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderLineProductService implements IOrderLineProductService {

    @Autowired
    private OrderLineProductRepository orderLineProductRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<OrderLineProduct> findAll() {
        return orderLineProductRepository.findAll();
    }

    @Override
    public OrderLineProduct findById(Long id) {
        if (orderLineProductRepository.findById(id).isPresent()){
            return orderLineProductRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public OrderLineProduct addOrderLineProduct(OrderLineProductDtoPost orderLineProductDTO) {
        OrderLineProductMapper mapper = new OrderLineProductMapper(modelMapper);

        OrderLineProduct orderLineProduct = new OrderLineProduct();

        OrderLine orderLine = orderLineRepository.findById(orderLineProductDTO.getOrder_line_id()).get();

        Product product = productRepository.findById(orderLineProductDTO.getProduct_id()).get();

        orderLine.setQuantity(orderLine.getQuantity() + 1);

        orderLineProduct.setOrderLine(orderLine);
        orderLineProduct.setProduct(product);

        orderLineRepository.save(orderLine);
        return orderLineProductRepository.save(orderLineProduct);
    }
}
