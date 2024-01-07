package shushi.orders.servcie;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.auth.entity.UserProfile;
import shushi.auth.repository.UserRepository;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;
import shushi.cart.repository.CartRepository;
import shushi.orders.dto.OrderRequest;
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
    public Map<String, Object> createOrder(OrderRequest orderRequest) {
        Optional<UserProfile> userProfileOptional = userRepository.findById(orderRequest.getUserId());

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

        System.out.println(totalAmount);

        OrderEntity order = OrderEntity.builder()
                .items(orderItems)
                .userId(orderRequest.getUserId())
                .date(LocalDateTime.now().toString())
                .totalAmount((int) totalAmount)
                .build();

        OrderEntity savedOrder = orderRepository.save(order);

        Map<String, Object> response = new HashMap<>();
        response.put("order", savedOrder);
        response.put("message", "Order created successfully.");

        return response;
    }


    @Override
    public OrderEntity getOrder(String userId, String orderId) {
        List<OrderEntity> orderEntities = orderRepository.findByUserId(userId);
        Optional<OrderEntity> targetOrder = orderEntities.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst();

        return targetOrder.orElse(null);
    }


    @Override
    public List<OrderEntity> getAllOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }


}