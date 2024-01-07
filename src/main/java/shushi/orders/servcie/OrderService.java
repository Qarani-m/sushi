package shushi.orders.servcie;

import shushi.orders.dto.OrderRequest;
import shushi.orders.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map<String, Object> createOrder(OrderRequest orderRequest);

    OrderEntity getOrder(String userId, String orderId);

    List<OrderEntity> getAllOrders(String userId);

}

