package shushi.orders.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.orders.dto.OrderRequestDto;
import shushi.orders.servcie.OrderService;

import java.util.Map;

@RestController
@RequestMapping("/api/private/orders")
public class OrderController {
    @Autowired
    private  OrderService orderService;



    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderRequestDto orderRequest) {
        Map<String, Object> orderCreationResult = orderService.createOrder(orderRequest);

        if (orderCreationResult.containsKey("message")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderCreationResult);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(orderCreationResult);
        }
    }
    // Endpoint to get details of a specific order
    @GetMapping("/one/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrder(@PathVariable String orderId, @RequestBody OrderRequestDto orderRequest) {
        Map<String, Object> orderResult = orderService.getOrder(orderRequest.getUserId(), orderId);

        if (orderResult.get("order") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderResult);
        } else {
            return ResponseEntity.ok(orderResult);
        }
    }
    // Endpoint to get all orders
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getAllOrders(@PathVariable String userId) {
        Map<String, Object> allOrdersResult = orderService.getAllOrders(userId);
        if (allOrdersResult.get("orders") == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(allOrdersResult);
        } else {
            return ResponseEntity.ok(allOrdersResult);
        }
    }
}
