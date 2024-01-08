package shushi.payments.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shushi.payments.dto.PaymentDto;
import shushi.payments.entity.PaymentEntity;
import shushi.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/private/payment")
public class PaymentControlelr {

    @Autowired
    private PaymentService paymentService;




    // Create a new payment
    @PostMapping("/create")
    public ResponseEntity<PaymentEntity> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentEntity createdPayment = paymentService.createPayment(paymentDto);
        return ResponseEntity.ok(createdPayment);
    }

    // Get details of a specific payment by ID
    @GetMapping("/one/{paymentId}")
    public ResponseEntity<Map<String, Object>> getPaymentById(@PathVariable String paymentId) {
        Map<String, Object> response = paymentService.getPaymentById(paymentId);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getAllPaymentsForUser(@PathVariable String userId) {
        Map<String, Object> response = paymentService.getAllPaymentsForUser(userId);

        if (!response.isEmpty() && response.get("payments") != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


//    i dont know why i would want anyone to have this functionalities

    // Update the status of a payment
    @PutMapping("/admin/update/{paymentId}")
    public ResponseEntity<PaymentEntity> updatePaymentStatus(
            @PathVariable String paymentId,
            @RequestParam String newStatus) {
        PaymentEntity updatedPayment = paymentService.updatePaymentStatus(paymentId, newStatus);
        return ResponseEntity.ok(updatedPayment);
    }

    // Delete a payment by ID
    @DeleteMapping("/admin/delete/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable String paymentId) {
        boolean deleted = paymentService.deletePayment(paymentId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
