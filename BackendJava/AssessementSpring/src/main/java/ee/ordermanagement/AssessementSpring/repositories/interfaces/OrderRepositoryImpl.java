package ee.ordermanagement.AssessementSpring.repositories.interfaces;

import ee.ordermanagement.AssessementSpring.mappers.OrderMapper;
import ee.ordermanagement.AssessementSpring.models.Order;
import ee.ordermanagement.AssessementSpring.models.dto.OrderDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDTO> findOrdersByCustomerIdCriteria(Long id) {
        String jpql = String.format("select o.id, o.date_of_submission, o.customer_id from orders o join order_lines ol on ol.order_id = o.id where customer_id = %s", id);
//        String jpql2 = "from Order";
        List<Order> resultList = entityManager.createNativeQuery(jpql, Order.class).getResultList();
        List<OrderDTO> resultList2 = resultList.stream().map(i -> orderMapper.orderToOrderDTO(i)).toList();
        for (OrderDTO orderDTO : resultList2) {
            orderDTO.setCustomer_id(id);
        }
//        List<OrderDTO> resultList2 = entityManager.createQuery(jpql, OrderDTO.class).getResultList();
        return resultList2;
    }

    @Override
    public List<OrderDTO> findOrdersByProductIdCriteria(Long id) {
        String jpql = String.format("select o.id, o.date_of_submission, o.customer_id from orders o join order_lines ol on ol.order_id = o.id join order_line_products olp on ol.id = olp.order_line_id where olp.product_id = %s", id);
        List<Order> resultList = entityManager.createNativeQuery(jpql, Order.class).getResultList();
        List<OrderDTO> resultList2 = resultList.stream().map(i -> orderMapper.orderToOrderDTO(i)).toList();
        for (OrderDTO orderDTO : resultList2) {
            orderDTO.setCustomer_id(id);
        }
        return resultList2;
    }
}
