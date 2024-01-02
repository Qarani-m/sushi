package shushi.orders.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shushi.orders.dto.OrderRequest;
import shushi.orders.entity.OrderEntity;
import shushi.orders.servcie.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/private/orders")
public class OrderController {
    @Autowired
    private  OrderService orderService;



    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    // Endpoint to get details of a specific order
    @GetMapping("/one/{orderId}")
    public OrderEntity getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    // Endpoint to get all orders
    @GetMapping
    public List<OrderEntity> getAllOrders(@PathVariable String userId) {
        return orderService.getAllOrders(userId);
    }

    // Endpoint to update an existing order
    @PutMapping("/{orderId}")
    public OrderEntity updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest orderRequest) {
        return orderService.updateOrder(orderId, orderRequest);
    }

    // Endpoint to delete an order
    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
