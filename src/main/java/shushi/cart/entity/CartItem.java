package shushi.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shushi.item.entity.ItemEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    private ItemEntity sushi;
    private int quantity;
}

