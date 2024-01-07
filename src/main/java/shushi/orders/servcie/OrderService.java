package shushi.orders.servcie;

import shushi.orders.dto.OrderRequestDto;
import shushi.orders.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map<String, Object> createOrder(OrderRequestDto orderRequest);

    Map<String, Object> getOrder(String userId, String orderId);

    Map<String, Object>  getAllOrders(String userId);

}

