package shushi.orders.dto;

import lombok.*;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderRequestDto {
    private String userId;
}