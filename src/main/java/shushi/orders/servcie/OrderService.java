package shushi.orders.servcie;

import shushi.orders.dto.OrderRequest;
import shushi.orders.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity createOrder(OrderRequest orderRequest);

    OrderEntity getOrder(String orderId);

    List<OrderEntity> getAllOrders(String userId);

    OrderEntity updateOrder(Long orderId, OrderRequest orderRequest);

    void deleteOrder(Long orderId);
}
