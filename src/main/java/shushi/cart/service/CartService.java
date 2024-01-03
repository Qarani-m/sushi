package shushi.cart.service;

import shushi.cart.dto.NewCartDto;
import shushi.cart.dto.NewItemDto;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;

public interface CartService {
    CartEntity addItemToCart(String userId, String item);

    CartEntity removeItemFromCart(String userId, String itemId);

    CartEntity getCart(String userId);

    void clearCart(String userId);

    CartEntity createCart(NewCartDto newCartDto);

    int getNumberOfItemsInCart(String userId);

    CartEntity increaseQuantity(String userId, String itemId);

    CartEntity reduceQuantity(String userId, String itemId);
}
