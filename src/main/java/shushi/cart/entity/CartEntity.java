package shushi.cart.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cart")
@Builder
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private List<CartItem> items = new ArrayList<>();

    private String userId;
}
