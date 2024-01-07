package shushi.orders.servcie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.auth.entity.UserProfile;
import shushi.auth.repository.UserRepository;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.repository.CartRepository;
import shushi.orders.dto.OrderRequestDto;
import shushi.orders.entity.OrderEntity;
import shushi.orders.repository.OrderRepository;
import shushi.sushi.entity.SushiEntity;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Map<String, Object> createOrder(OrderRequestDto orderRequest) {
        CartEntity cartEntity = cartRepository.findByUserId(orderRequest.getUserId());
        if (cartEntity == null || cartEntity.getItems().isEmpty()) {
            return Collections.singletonMap("message", "Cart is empty. Order creation failed.");
        }

        List<Map<String, Integer>> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (CartItem cartItem : cartEntity.getItems()) {
            SushiEntity sushiEntity = cartItem.getSushi();
            int quantity = cartItem.getQuantity();
            double totalPrice = quantity * sushiEntity.getPrice();
            totalAmount += totalPrice;

            orderItems.add(Map.of(sushiEntity.getId(), quantity));
        }

        OrderEntity order = OrderEntity.builder()
                .items(orderItems)
                .userId(orderRequest.getUserId())
                .date(LocalDateTime.now().toString())
                .totalAmount((int) totalAmount)
                .build();

        OrderEntity savedOrder = orderRepository.save(order);

        Map<String, Object> response = new HashMap<>();
        response.put("order", savedOrder);
        response.put("message", "success");

        return response;
    }

    @Override
    public Map<String, Object> getOrder(String userId, String orderId) {
        List<OrderEntity> orderEntities = orderRepository.findByUserId(userId);
        Optional<OrderEntity> targetOrder = orderEntities.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst();
        Map<String, Object> response = new HashMap<>();
        if (targetOrder.isPresent()) {
            response.put("order", targetOrder.get());
            response.put("message", "success");
        } else {
            response.put("order", null);
            response.put("message", "Order not found.");
        }
        return response;
    }

    @Override
    public Map<String, Object> getAllOrders(String userId) {
        List<OrderEntity> allOrders = orderRepository.findByUserId(userId);

        Map<String, Object> response = new HashMap<>();

        if (!allOrders.isEmpty()) {
            response.put("orders", allOrders);
            response.put("message", "success");
        } else {
            response.put("orders", null);
            response.put("message", "No orders found.");
        }

        return response;
    }


}