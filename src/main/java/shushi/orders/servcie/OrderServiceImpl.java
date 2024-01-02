package shushi.orders.servcie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.orders.dto.OrderRequest;
import shushi.orders.entity.OrderEntity;
import shushi.orders.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderEntity createOrder(OrderRequest orderRequest) {

        OrderEntity order = OrderEntity.builder()
                .userId(orderRequest.getUserId())
                .build();

        return orderRepository.save(order);


    }

















    @Override
    public OrderEntity getOrder(String orderId) {
        return null;
    }

    @Override
    public List<OrderEntity> getAllOrders(String userId) {
        return null;
    }

    @Override
    public OrderEntity updateOrder(Long orderId, OrderRequest orderRequest) {
        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

    }
}
