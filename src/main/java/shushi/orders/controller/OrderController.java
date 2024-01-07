package shushi.orders.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.orders.dto.OrderRequest;
import shushi.orders.entity.OrderEntity;
import shushi.orders.servcie.OrderService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/private/orders")
public class OrderController {
    @Autowired
    private  OrderService orderService;



    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequest orderRequest) {
        Map<String, Object> orderCreationResult = orderService.createOrder(orderRequest);

        if (orderCreationResult.containsKey("message")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderCreationResult);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderCreationResult);
        }
    }
    // Endpoint to get details of a specific order
    @GetMapping("/one/{orderId}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable String orderId, @RequestBody OrderRequest orderRequest) {
        OrderEntity foundOrder = orderService.getOrder(orderRequest.getUserId(), orderId);

        return foundOrder != null ?
                ResponseEntity.ok(foundOrder) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint to get all orders
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<OrderEntity>> getAllOrders(@PathVariable String userId) {
        List<OrderEntity> allOrders = orderService.getAllOrders(userId);
        return ResponseEntity.ok(allOrders);
    }



}
