package shushi.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import shushi.sushi.entity.SushiEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartItem {

    @DBRef
    private SushiEntity sushi;
    private int quantity;
}

