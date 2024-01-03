package shushi.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import shushi.sushi.entity.SushiEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    private SushiEntity sushi;
    private int quantity;
}

