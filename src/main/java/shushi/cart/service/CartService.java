package shushi.cart.service;

import shushi.cart.dto.NewCartDto;
import shushi.cart.dto.NewItemDto;
import shushi.cart.entity.CartEntity;
import shushi.cart.entity.CartItem;

import java.util.Map;

public interface CartService {
    Map<String, Object> addItemToCart(String userId, String item);

    Map<String, Object> removeItemFromCart(String userId, String itemId);

    Map<String, Object> getCart(String userId);

    Map<String, Object> clearCart(String userId);

    Map<String, Object> createCart(NewCartDto newCartDto);

    Map<String, Object> getNumberOfItemsInCart(String userId);

    Map<String, Object> increaseQuantity(String userId, String itemId);

    Map<String, Object> reduceQuantity(String userId, String itemId);
}
