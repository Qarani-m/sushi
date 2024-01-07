package shushi.payments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {
    private String orderId;
    private String userId;
    private double amount;
    private String refId;
    private String status;
    private String paymentMethod;
    private String paymentDate;
}