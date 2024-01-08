package shushi.payments.service;

import shushi.payments.dto.PaymentDto;
import shushi.payments.entity.PaymentEntity;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    // Create a new payment
    PaymentEntity createPayment(PaymentDto paymentDto);

    // Get details of a specific payment by ID
    Map<String, Object> getPaymentById(String paymentId);

    // Get all payments for a specific user
    Map<String, Object> getAllPaymentsForUser(String userId);

    // Update the status of a payment
    PaymentEntity updatePaymentStatus(String paymentId, String newStatus);

    // Delete a payment by ID
    boolean deletePayment(String paymentId);
}

