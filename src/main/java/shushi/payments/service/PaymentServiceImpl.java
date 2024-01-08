package shushi.payments.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.payments.dto.PaymentDto;
import shushi.payments.entity.PaymentEntity;
import shushi.payments.repository.PaymentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public PaymentEntity createPayment(PaymentDto paymentDto) {
        return null;
    }

    @Override
    public Map<String, Object> getPaymentById(String paymentId) {
        Optional<PaymentEntity> optionalPayment = paymentRepository.findById(paymentId);

        Map<String, Object> response = new HashMap<>();
        if (optionalPayment.isPresent()) {
            response.put("payment", optionalPayment.get());
            response.put("message", "success");
        } else {
            response.put("payment", null);
            response.put("message", "No payment found for the given ID.");
        }

        return response;
    }


    @Override
    public Map<String, Object> getAllPaymentsForUser(String userId) {
        List<PaymentEntity> userPayments = paymentRepository.findByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        if (!userPayments.isEmpty()) {
            response.put("payments", userPayments);
            response.put("message", "success");
        } else {
            response.put("payments", null);
            response.put("message", "No payments found for the user.");
        }

        return response;
    }


//    i dont know why i would want anyone to have this functionalities they stay null henceforth
    @Override
    public PaymentEntity updatePaymentStatus(String paymentId, String newStatus) {
        return null;
    }

    @Override
    public boolean deletePayment(String paymentId) {
        return false;
    }
}
